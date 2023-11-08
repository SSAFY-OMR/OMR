'use client';

import React, { useState } from 'react';

import { useRouter } from 'next/navigation';
import { PrevPageIcon } from 'public/icons';

import styles from './index.module.scss';

import type { Question } from '@/types/question';

import AnswerInput from '@/components/AnswerInput';
import AnswerListView from '@/components/AnswerListView';
import QuestionView from '@/components/QuestionView';
import QuestionViewLoading from '@/components/QuestionView/QuestionViewLoading';
import Button from '@/components/UI/Button';
import Toast from '@/components/UI/Toast';
import useFetcher from '@/hooks/useFetcher';
import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { updateScrap } from '@/service/question';
import { toastMessageState } from '@/states/ui';
import { NEUTRAL_500 } from '@/styles/color';

const QuestionDetailPage = ({
  params,
}: {
  params: { category: string; id: string };
}) => {
  const category = params.category;
  const id = params.id;

  const router = useRouter();

  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );
  const [viewAnswer, setViewAnswer] = useState(false);

  const { data: question, mutate } = useFetcher<Question>(
    `/questions/${id}`,
    typeof id !== 'undefined',
  );

  const toggleScrap = async (questionId: string) => {
    if (question) {
      const updatedQuestion: Question = {
        ...question,
        isScrapped: !question?.isScrapped,
      };

      await mutate(updatedQuestion, {
        revalidate: false,
        rollbackOnError: true,
      });
      const res = await updateScrap(questionId);
      if (res?.status === 200) {
        if (question.isScrapped) {
          setToastMessage('문제 스크랩을 취소했어요.');
        } else {
          setToastMessage('문제를 스크랩 했어요.');
        }
      }
    }
  };

  const handleClickAnswerList = () => {
    setViewAnswer(true);
  };

  const handleClickAnswerWrite = () => {
    setViewAnswer(false);
  };

  const handleClickNextBtn = () => {
    if (!question?.nextQuestionId) {
      setToastMessage('마지막 문제예요.');
      return;
    }
    router.push(
      `/question/${question.category.name}/${question?.nextQuestionId}`,
    );
  };

  const handleCloseToast = () => {
    setToastMessage('');
  };

  const handleClickBackBtn = () => {
    router.push(`/questionlist/${category}`);
  };

  return (
    <div className={`${styles.QuestionDetailPage}`}>
      <div className={styles.header}>
        <div onClick={handleClickBackBtn} className={styles.backBtn}>
          <PrevPageIcon width={24} height={24} fill={NEUTRAL_500} />
          <span className={styles.categoryTitle}>
            {category === 'ALL' ? '전체' : question?.category.description}
          </span>
        </div>
      </div>
      <div
        className={`${styles.questionSolving} ${
          viewAnswer ? styles.answerListPage : ''
        }`}
      >
        <div className={styles.QuestionContainer}>
          {question ? (
            <QuestionView
              listCategory={category}
              questionId={id}
              question={question}
              type="questionView"
              toggleScrap={toggleScrap}
            />
          ) : (
            <QuestionViewLoading />
          )}
          {!viewAnswer && <AnswerInput questionId={id} type={'edit'} />}
        </div>
        <div className={styles.btnGroup}>
          {viewAnswer ? (
            <Button
              size={'medium'}
              color={'primary'}
              width={'fitContent'}
              iconType={'edit'}
              onClick={handleClickAnswerWrite}
            >
              답 입력하기
            </Button>
          ) : (
            <Button
              size={'medium'}
              color={'primary'}
              width={'fitContent'}
              iconType={'community'}
              onClick={handleClickAnswerList}
            >
              답변 보기
            </Button>
          )}
          <Button
            size={'medium'}
            color={question?.nextQuestionId ? 'secondary' : 'disabled'}
            width={'fitContent'}
            iconType={'arrow'}
            onClick={handleClickNextBtn}
          >
            다음 문제
          </Button>
        </div>
        {viewAnswer && <AnswerListView questionId={id} />}
        <Toast
          message={toastMessage}
          isShown={toastMessage !== ''}
          onClose={handleCloseToast}
        />
      </div>
    </div>
  );
};

export default QuestionDetailPage;

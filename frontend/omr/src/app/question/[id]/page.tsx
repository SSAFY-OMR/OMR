'use client';

import React, { useState } from 'react';

import styles from './index.module.scss';

import type { Question } from '@/types/question';

import AnswerInput from '@/components/AnswerInput';
import QuestionView from '@/components/QuestionView';
import Button from '@/components/UI/Button';
import Toast from '@/components/UI/Toast';
import useFetcher from '@/hooks/useFetcher';
import { updateScrap } from '@/service/question';

const QuestionDetailPage = ({ params }: { params: { id: string } }) => {
  const id = params.id;

  const [toastMessage, setToastMessage] = useState('');

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

  const handleCloseToast = () => {
    setToastMessage('');
  };

  return (
    <div className={styles.QuestionDetailPage}>
      {question && (
        <div className={styles.QuestionContainer}>
          <QuestionView
            questionId={id}
            question={question}
            type="questionView"
            toggleScrap={toggleScrap}
          />
          {question.answer ? (
            <AnswerInput
              questionId={id}
              type={'read'}
              content={question.answer}
            />
          ) : (
            <AnswerInput questionId={id} type={'edit'} />
          )}
        </div>
      )}
      <div className={styles.btnGroup}>
        <Button
          size={'large'}
          color={'primary'}
          width={'fitContent'}
          iconType={'community'}
        >
          다른 사람 답변 보기
        </Button>
        <Button
          size={'large'}
          color={'secondary'}
          width={'fitContent'}
          iconType={'arrow'}
        >
          다음 문제 풀기
        </Button>
      </div>
      <Toast
        message={toastMessage}
        isShown={toastMessage !== ''}
        onClose={handleCloseToast}
      />
    </div>
  );
};

export default QuestionDetailPage;

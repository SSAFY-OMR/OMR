'use client';

import React, { useEffect, useState } from 'react';

import styles from './index.module.scss';

import type { Question } from '@/types/question';

import AnswerInput from '@/components/AnswerInput';
import QuestionView from '@/components/QuestionView';
import Button from '@/components/UI/Button';
import { getQuestionDetail } from '@/service/question';

const QuestionDetailPage = ({ params }: { params: { id: string } }) => {
  const [question, setQuestion] = useState<Question | undefined>(undefined);

  const id = params.id;

  useEffect(() => {
    (async () => {
      const res = await getQuestionDetail(id);
      if (res?.status === 200) {
        setQuestion(res.data.data);
      }
    })();
  }, [id]);

  return (
    <div className={styles.QuestionDetailPage}>
      {question && (
        <div className={styles.QuestionContainer}>
          <QuestionView question={question} type="questionView" />
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
    </div>
  );
};

export default QuestionDetailPage;

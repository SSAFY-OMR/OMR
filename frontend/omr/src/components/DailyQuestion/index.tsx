'use client';

import React, { useEffect, useState } from 'react';

import styles from './index.module.scss';
import QuestionView from '../QuestionView';
import QuestionViewLoading from '../QuestionView/QuestionViewLoading';

import type { Question } from '@/types/question';

import { getDailyQuestion } from '@/service/question';

const DailyQuestion = () => {
  const [question, setQuestion] = useState<Question | undefined>(undefined);

  useEffect(() => {
    (async () => {
      const res = await getDailyQuestion();
      if (res) {
        setQuestion(res.data.data);
      }
    })();
  }, []);

  return (
    <div className={styles.DailyQuestion}>
      <div className={styles.title}>오늘의 문제</div>
      {question ? (
        <QuestionView
          listCategory={question.category.name}
          question={question}
          type="listItem"
        />
      ) : (
        <QuestionViewLoading />
      )}
    </div>
  );
};

export default DailyQuestion;

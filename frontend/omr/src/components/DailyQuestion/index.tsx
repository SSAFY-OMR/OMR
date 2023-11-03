import React from 'react';

import styles from './index.module.scss';
import QuestionView from '../QuestionView';

import type { Question } from '@/types/question';

import { getDailyQuestion } from '@/service/question';

const DailyQuestion = async () => {
  const res = await getDailyQuestion();
  const question: Question = res.data.data;

  return (
    <div className={styles.DailyQuestion}>
      <div className={styles.title}>오늘의 문제가 도착했어요.</div>
      <QuestionView question={question} type="listItem" />
    </div>
  );
};

export default DailyQuestion;

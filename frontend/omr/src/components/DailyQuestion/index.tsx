import React from 'react';

import styles from './index.module.scss';
import QuestionView from '../QuestionView';

import { getDailyQuestion } from '@/service/question';

const DailyQuestion = async () => {
  const res = await getDailyQuestion();
  let question = undefined;
  if (res) {
    question = res.data.data;
  }

  return (
    <div className={styles.DailyQuestion}>
      <div className={styles.title}>오늘의 문제</div>
      {question && (
        <QuestionView
          listCategory={question.category.name}
          question={question}
          type="listItem"
        />
      )}
    </div>
  );
};

export default DailyQuestion;

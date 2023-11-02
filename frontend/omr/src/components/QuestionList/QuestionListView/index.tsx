import React from 'react';

import styles from './index.module.scss';

import type { Question } from '@/types/question';

import QuestionView from '@/components/QuestionView';

type QuestionListViewProps = {
  questions: Question[];
};

const QuestionListView = ({ questions }: QuestionListViewProps) => {
  return (
    <div className={styles.QuestionListView}>
      {questions.map((question, index) => (
        <QuestionView key={index} question={question} type={'listItem'} />
      ))}
    </div>
  );
};

export default QuestionListView;

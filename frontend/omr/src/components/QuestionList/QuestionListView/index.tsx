import React from 'react';

import styles from './index.module.scss';

import type { Question } from '@/types/question';

import QuestionView from '@/components/QuestionView';

type QuestionListViewProps = {
  questions: Question[];
  listCategory: string;
};

const QuestionListView = ({
  questions,
  listCategory,
}: QuestionListViewProps) => {
  return (
    <div className={styles.QuestionListView}>
      {questions.map((question, index) => (
        <QuestionView
          key={index}
          listCategory={listCategory}
          question={question}
          type={'listItem'}
        />
      ))}
    </div>
  );
};

export default QuestionListView;

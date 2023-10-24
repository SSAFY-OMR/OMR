import React from 'react';

import { BookmarkIcon } from 'public/icons';

import styles from './index.module.scss';
import CategoryTag from '../CategoryTag';

import type { Question } from '@/types/question';

import { NEUTRAL_60 } from '@/styles/color';

type QuestionProps = {
  question: Question;
};

const QuestionView = ({ question }: QuestionProps) => {
  return (
    <div className={styles.QuestionView}>
      <div className={styles.header}>
        <span className={styles.category}>
          <CategoryTag category={question.category} />
        </span>
        <BookmarkIcon width={'24px'} height={'24px'} fill={NEUTRAL_60} />
      </div>
      <div className={styles.questionContent}>{question.content}</div>
    </div>
  );
};

export default QuestionView;

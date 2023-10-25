import React from 'react';

import { ArrowIcon, BookmarkIcon } from 'public/icons';

import styles from './index.module.scss';
import CategoryTag from '../CategoryTag';
import ScrapButton from '../ScrapButton';

import type { Question } from '@/types/question';

import { BLUE_600, NEUTRAL_60 } from '@/styles/color';

type QuestionProps = {
  question: Question;
  type: 'listItem' | 'questionView';
};

const QuestionView = ({ question, type }: QuestionProps) => {
  return (
    <div className={styles.QuestionView}>
      <div className={styles.header}>
        <span className={styles.category}>
          <CategoryTag category={question.category} />
        </span>
        {type === 'questionView' && (
          <ScrapButton isScrapped={question.isScrapped} />
        )}
      </div>
      <div className={`${styles.questionContent} ${styles[type]}`}>
        {question.content}
      </div>
      {type === 'listItem' && (
        <div className={styles.footer}>
          <button>
            <ArrowIcon width={'24px'} height={'24px'} fill={BLUE_600} />
          </button>
        </div>
      )}
    </div>
  );
};

export default QuestionView;

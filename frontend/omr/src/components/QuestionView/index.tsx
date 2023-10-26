import React from 'react';

import { ArrowIcon } from 'public/icons';

import styles from './index.module.scss';
import CategoryTag from '../CategoryTag';
import ScrapButton from '../ScrapButton';

import type { Question } from '@/types/question';

import { BLUE_600 } from '@/styles/color';

type QuestionProps = {
  question: Question;
  type: 'listItem' | 'questionView';
};

const QuestionView = ({ question, type }: QuestionProps) => {
  return (
    <div className={styles.QuestionView}>
      <div className={styles.header}>
        <span className={styles.category}>
          <CategoryTag category={question.category.description} />
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
            <ArrowIcon width={24} height={24} fill={BLUE_600} />
          </button>
        </div>
      )}
    </div>
  );
};

export default QuestionView;

import React from 'react';

import { ErrorIcon, SuccessIcon } from 'public/icons';

import styles from './index.module.scss';

import { GREEN, RED } from '@/styles/color';

type FeedbackMessageProps = {
  children: React.ReactNode;
  type: 'error' | 'success';
};

const FeedbackMessage = ({ children, type }: FeedbackMessageProps) => {
  return (
    <div className={`${styles.FeedbackMessage}`}>
      {type === 'error' ? (
        <ErrorIcon width={20} height={20} fill={RED} />
      ) : (
        <SuccessIcon width={20} height={20} fill={GREEN} />
      )}
      <div className={`${styles.message} ${styles[type]}`}>{children}</div>
    </div>
  );
};

export default FeedbackMessage;

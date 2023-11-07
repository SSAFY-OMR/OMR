import React from 'react';

import styles from './index.module.scss';

const QuestionViewLoading = () => {
  return (
    <div className={styles.QuestionView}>
      <div className={`${styles.header} ${styles.blinking}`}></div>
      <div className={`${styles.questionContent} ${styles.blinking}`}></div>
    </div>
  );
};

export default QuestionViewLoading;

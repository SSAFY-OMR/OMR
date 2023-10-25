import React from 'react';

import styles from './index.module.scss';

const AnswerInput = () => {
  return (
    <div className={styles.AnswerInput}>
      <div className={styles.answerAreaWrapper}>
        <div className={styles.answerArea} contentEditable />
      </div>
    </div>
  );
};

export default AnswerInput;

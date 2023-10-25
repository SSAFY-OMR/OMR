import React from 'react';

import styles from './index.module.scss';
import Button from '../UI/Button';

type AnswerInputProps = {
  type: 'edit' | 'read';
  content?: string;
};

const AnswerInput = ({ type, content }: AnswerInputProps) => {
  return (
    <div className={styles.AnswerInput}>
      {type === 'edit' ? (
        <div className={styles.answerAreaWrapper}>
          <div className={styles.answerArea} contentEditable />
        </div>
      ) : (
        <div className={styles.answerAreaWrapper}>
          <div className={styles.answerArea}>{content}</div>
        </div>
      )}
      <div className={styles.saveBtn}>
        {type === 'edit' ? (
          <Button
            color="secondary"
            size="small"
            type="complete"
            width="fitContent"
          >
            저장
          </Button>
        ) : (
          <Button color="secondary" size="small" type="edit" width="fitContent">
            수정
          </Button>
        )}
      </div>
    </div>
  );
};

export default AnswerInput;

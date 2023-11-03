'use client';

import React, { useRef, useState } from 'react';

import styles from './index.module.scss';
import Button from '../UI/Button';
import Toast from '../UI/Toast';

import { createAnswer } from '@/service/answer';

type AnswerInputProps = {
  questionId: string;
  type: 'edit' | 'read';
  content?: string;
};

const AnswerInput = ({ questionId, type, content }: AnswerInputProps) => {
  // eslint-disable-next-line no-null/no-null
  const answerArea = useRef<HTMLDivElement>(null);

  const [answerContent, setAnswerContent] = useState('');
  const [toastMessage, setToastMessage] = useState('');

  const handleClickAnswerArea = () => {
    if (answerArea.current) {
      answerArea.current.focus();
    }
  };

  const handleInputAnswer = (e: React.FormEvent<HTMLDivElement>) => {
    if (e.currentTarget.textContent) {
      setAnswerContent(e.currentTarget.textContent);
    }
  };

  const handleClickSaveBtn = async () => {
    const res = await createAnswer({
      questionId: questionId,
      content: answerContent,
    });
    console.log(res);

    if (res?.status === 200) {
      setToastMessage('답안을 저장했어요.');
    }
  };

  const handleClickEditBtn = () => {};

  const handleCloseToast = () => {
    setToastMessage('');
  };

  return (
    <div className={styles.AnswerInput} onClick={handleClickAnswerArea}>
      {type === 'edit' ? (
        <div className={styles.answerAreaWrapper}>
          <div
            className={styles.answerArea}
            ref={answerArea}
            onInput={(e) => handleInputAnswer(e)}
            contentEditable
            suppressContentEditableWarning
          />
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
            iconType="complete"
            width="fitContent"
            onClick={handleClickSaveBtn}
          >
            저장
          </Button>
        ) : (
          <Button
            color="secondary"
            size="small"
            iconType="edit"
            width="fitContent"
            onClick={handleClickEditBtn}
          >
            수정
          </Button>
        )}
      </div>
      <Toast
        message={toastMessage}
        isShown={toastMessage !== ''}
        onClose={handleCloseToast}
      />
    </div>
  );
};

export default AnswerInput;

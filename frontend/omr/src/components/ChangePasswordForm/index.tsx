'use client';

import React, { useState } from 'react';

import { useForm } from 'react-hook-form';

import { updateUserPassword } from '@/service/member';

import styles from './index.module.scss';
import FeedbackMessage from '../FeedbackMessage';
import Button from '../UI/Button';
import Toast from '../UI/Toast';

import type { FieldValues } from 'react-hook-form';

const ChangePasswordForm = () => {
  const {
    register,
    watch,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: 'onChange' });

  const [toastMessage, setToastMessage] = useState('');

  const handleSaveButton = async (data: FieldValues) => {
    const res = await updateUserPassword(data.password);
    
    if (res?.status === 200) {
      setToastMessage('비밀번호 변경에 성공하였습니다. 🤗');
    } else {
      setToastMessage('비밀번호 변경에 실패하였습니다. 관리자에게 문의하세요. 😔');
    }
  };

  const handleCloseToast = () => {
    setToastMessage('');
  };

  return (
    <form className={styles.UpdatePasswordForm}>
      <div className={styles.profileContent}>
        <label htmlFor="password" className={styles.label}>
        비밀번호 변경
        </label>
        <input
          id="password"
          type="password"
          placeholder="비밀번호를 입력하세요."
          className={styles.labelContent}
          {...register('password', {
            required: '비밀번호를 입력해주세요.',
            pattern: {
                value: /^[A-Za-z0-9]+$/,
                message: '비밀번호는 영문과 숫자로 구성되어야 합니다.',
            },
            minLength: {
                value: 8,
                message: '비밀번호는 8자 이상이어야 합니다.',
            },
            maxLength: {
                value: 16,
                message: '비밀번호는 16자 이하여야 합니다.',
            }}
          )}
        />
      </div>
      <div className="validateMessage">
        {errors.password && (
          <FeedbackMessage type="error">
          {errors.password.message?.toString()}
          </FeedbackMessage>
        )}
      </div>
          
      <div className={styles.profileContent}>
        <label htmlFor="passwordConfirm" className={styles.label}>
          비밀번호 확인
        </label>
        <input
          id="passwordConfirm"
          type="password"
          placeholder="비밀번호를 입력하세요."
          className={styles.labelContent}
            {...register('passwordConfirm', {
            required: '비밀번호를 확인해주세요.'
            })}
        />
      </div>
        
      <div className="validateMessage">
        { (watch("password") !== watch("passwordConfirm")) ?
            <FeedbackMessage type="error">
              비밀번호가 일치하지 않습니다.
            </FeedbackMessage> :
            watch("password") && !errors.password && <FeedbackMessage type="success">
              비밀번호가 일치합니다.
            </FeedbackMessage> 
        }
      </div>

      <div className={styles.btnGroup}>
        <Button
          color="primary"
          size="medium"
          type="submit"
          width="fitContent"
          iconType="complete"
          onClick={handleSubmit(handleSaveButton)}
          >
            저장
        </Button>
      </div>
      <Toast
        message={toastMessage}
        isShown={toastMessage !== ''}
        onClose={handleCloseToast}
      />
    </form>
  );
};

export default ChangePasswordForm;
'use client';

import React, { useState } from 'react';

import { useRouter } from 'next/navigation';
import { useForm } from 'react-hook-form';

import styles from './index.module.scss';
import FeedbackMessage from '../FeedbackMessage';
import Button from '../UI/Button';
import Toast from '../UI/Toast';

import type { FieldValues } from 'react-hook-form';

import { getExistence } from '@/service/auth';
import { signUp } from '@/service/member';

const SignUpForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({ mode: 'onChange' });

  const router = useRouter();

  const [isLoginSucceed, setIsLoginSucceed] = useState(true);
  const [ixExists, setIsExists] = useState(false);
  const [toastMessage, setToastMessage] = useState('');

  const handleSignUp = async (data: FieldValues) => {
    const existRes = await getExistence(data.loginId);
    const exist = existRes?.data.data.isExist!;
    setIsExists(exist);
    console.log(existRes);

    if (exist) return;

    const res = await signUp({
      loginId: data.loginId,
      password: data.password,
      emoji: '😀',
    });

    if (res?.status === 200) {
      setToastMessage('회원가입에 성공했어요. 환영합니다! 🤗');
      console.log(res);

      router.replace('/login');
    } else {
      setIsLoginSucceed(false);
    }
  };

  const handleCloseToast = () => {
    setToastMessage('');
  };

  return (
    <form className={styles.LoginForm}>
      <div className={styles.loginInputs}>
        <div className={styles.inputItem}>
          <label htmlFor="loginId" className={styles.label}>
            아이디
          </label>
          <input
            id="loginId"
            type="text"
            placeholder="아이디를 입력하세요."
            className={styles.loginInput}
            {...register('loginId', {
              required: '아이디를 입력해주세요.',
              pattern: {
                value: /^[a-z][a-z0-9]*[0-9][a-z0-9]*$/,
                message: '아이디는 영문과 숫자로 구성되어야 합니다.',
              },
              minLength: {
                value: 8,
                message: '아이디는 8자 이상이어야 합니다.',
              },
              maxLength: {
                value: 16,
                message: '아이디는 16자 이하여야 합니다.',
              },
            })}
          />
          {errors.loginId && (
            <FeedbackMessage type="error">
              {errors.loginId.message?.toString()}
            </FeedbackMessage>
          )}
          {!errors.loginId && ixExists && (
            <FeedbackMessage type="error">
              이미 가입된 아이디입니다.
            </FeedbackMessage>
          )}
        </div>
        <div className={styles.inputItem}>
          <label htmlFor="password" className={styles.label}>
            비밀번호
          </label>
          <input
            id="password"
            type="password"
            placeholder="비밀번호를 입력하세요."
            className={styles.loginInput}
            {...register('password', {
              required: '비밀번호를 입력해주세요.',
              pattern: {
                value: /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]+$/,
                message: '비밀번호는 영문과 숫자로 구성되어야 합니다.',
              },
              minLength: {
                value: 8,
                message: '비밀번호는 8자 이상이어야 합니다.',
              },
              maxLength: {
                value: 16,
                message: '비밀번호는 16자 이하여야 합니다.',
              },
            })}
          />
          {errors.password && (
            <FeedbackMessage type="error">
              {errors.password.message?.toString()}
            </FeedbackMessage>
          )}
        </div>
        <div className={styles.inputItem}>
          <label htmlFor="confirmPassword" className={styles.label}>
            비밀번호 확인
          </label>
          <input
            id="confirmPassword"
            type="password"
            placeholder="비밀번호를 한 번 더 입력해주세요."
            className={styles.loginInput}
            {...register('confirmPassword', {
              required: '비밀번호를 한 번 더 입력해주세요.',
              validate: (value) =>
                value === watch('password') || '비밀번호가 일치하지 않습니다.',
            })}
          />
          {errors.confirmPassword && (
            <FeedbackMessage type="error">
              {errors.confirmPassword.message?.toString()}
            </FeedbackMessage>
          )}
          {!errors.confirmPassword &&
            watch('password') &&
            watch('confirmPassword') === watch('password') && (
              <FeedbackMessage type="success">
                비밀번호가 일치합니다.
              </FeedbackMessage>
            )}
        </div>
      </div>
      <div className={styles.btnGroup}>
        <Button
          color="primary"
          size="medium"
          type="submit"
          width="full"
          iconType="complete"
          onClick={handleSubmit(handleSignUp)}
        >
          회원가입
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

export default SignUpForm;

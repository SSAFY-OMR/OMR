'use client';

import React, { useState } from 'react';

import { useRouter } from 'next/navigation';
import { useForm } from 'react-hook-form';

import styles from './index.module.scss';
import FeedbackMessage from '../FeedbackMessage';
import Button from '../UI/Button';
import Toast from '../UI/Toast';

import type { FieldValues } from 'react-hook-form';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { login } from '@/service/auth';
import { userAccessTokenState, userRefreshTokenState } from '@/states/auth';
import { toastMessageState } from '@/states/ui';

const LoginForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: 'onChange' });

  const router = useRouter();

  const [isLoginSucceed, setIsLoginSucceed] = useState(true);
  const [userAccessToken, setUserAccessToken] = useSSRRecoilState(
    userAccessTokenState,
    '',
  );
  const [userRefreshToken, setUserRefreshToken] = useSSRRecoilState(
    userRefreshTokenState,
    '',
  );
  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const handleLogin = async (data: FieldValues) => {
    const res = await login({ loginId: data.loginId, password: data.password });

    if (res?.status === 200) {
      setUserAccessToken(res.data.data.accessToken);
      setUserRefreshToken(res.data.data.refreshToken);
      router.replace('/');
    } else {
      setIsLoginSucceed(false);
    }
  };

  const hadleClickJoin = () => {
    router.push('/signup');
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
                value: /^[a-z]+[a-z0-9]{7,15}$/,
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
                value:
                  /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/,
                message:
                  '비밀번호는 영문과 숫자, 특수문자로 구성되어야 합니다.',
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
          {!isLoginSucceed && !errors.password && (
            <FeedbackMessage type="error">
              아이디 또는 비밀번호가 일치하지 않습니다.
            </FeedbackMessage>
          )}
          {errors.password && (
            <FeedbackMessage type="error">
              {errors.password.message?.toString()}
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
          onClick={handleSubmit(handleLogin)}
        >
          로그인
        </Button>
        <Button
          color="secondary"
          size="medium"
          type="button"
          width="full"
          iconType="arrow"
          onClick={hadleClickJoin}
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

export default LoginForm;

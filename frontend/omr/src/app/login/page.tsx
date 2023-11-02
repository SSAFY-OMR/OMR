import React from 'react';

import styles from './index.module.scss';

import LoginForm from '@/components/LoginForm';

const LoginPage = () => {
  return (
    <div className={styles.LoginPage}>
      <div className={styles.title}>로그인</div>
      <LoginForm />
    </div>
  );
};

export default LoginPage;

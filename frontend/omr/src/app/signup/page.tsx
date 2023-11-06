import React from 'react';

import styles from './index.module.scss';

import SignUpForm from '@/components/SignUpForm';

const SignUpPage = () => {
  return (
    <div className={styles.SignUpPage}>
      <div className={styles.title}>회원가입</div>
      <SignUpForm />
    </div>
  );
};

export default SignUpPage;

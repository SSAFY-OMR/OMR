import React from 'react';

import styles from './index.module.scss';
import Button from '../UI/Button';

const BeforeLogin = () => {
  return (
    <div className={styles.beforeLogin}>
      <div className={styles.loginMessage}>로그인 해주세요!</div>
      <Button
        size={'medium'}
        color={'primary'}
        width={'fitContent'}
        iconType={'arrow'}
      >
        로그인 하러 가기
      </Button>
    </div>
  );
};

export default BeforeLogin;

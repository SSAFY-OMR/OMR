import React from 'react';

import { useRouter } from 'next/navigation';

import styles from './index.module.scss';
import Button from '../UI/Button';

const BeforeLogin = () => {
  const router = useRouter();

  const handleMoveLogin = () => {
    router.push('/login');
  };

  return (
    <div className={styles.beforeLogin}>
      <div className={styles.loginMessage}>로그인 해주세요!</div>
      <Button
        size={'medium'}
        color={'primary'}
        width={'fitContent'}
        iconType={'arrow'}
        onClick={handleMoveLogin}
      >
        로그인 하러 가기
      </Button>
    </div>
  );
};

export default BeforeLogin;

import React, { useEffect } from 'react';

import styles from './index.module.scss';

type ToastProps = {
  message: string;
  isShown: boolean;
  onClose: any;
};

const Toast = ({ message, isShown, onClose }: ToastProps) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose();
    }, 1000);
    return clearTimeout(timer);
  }, [onClose]);

  return (
    <div className={`${styles.Toast} ${isShown ? `${styles.show}` : ''}`}>
      {message}
    </div>
  );
};

export default Toast;

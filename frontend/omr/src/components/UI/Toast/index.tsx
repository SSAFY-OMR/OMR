import React, { useEffect } from 'react';

import styles from './index.module.scss';
import Portal from '../Portal';

type ToastProps = {
  message: string;
  isShown: boolean;
  onClose: any;
};

const Toast = ({ message, isShown, onClose }: ToastProps) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose();
    }, 1300);
    return () => clearTimeout(timer);
  }, [onClose]);

  return (
    <Portal selector="#portal">
      <div className={`${styles.Toast} ${isShown ? `${styles.show}` : ``}`}>
        {message}
      </div>
    </Portal>
  );
};

export default Toast;

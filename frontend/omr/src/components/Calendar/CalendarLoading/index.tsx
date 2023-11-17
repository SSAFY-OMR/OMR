import React from 'react';

import styles from './index.module.scss';

const CalendarLoading = () => {
  return (
    <div className={styles.container}>
      <div className={`${styles.title} ${styles.blinking}`}></div>
      <div className={`${styles.title} ${styles.blinking}`}></div>
      <div className={`${styles.calendar} ${styles.blinking}`}></div>
    </div>
  );
};

export default CalendarLoading;

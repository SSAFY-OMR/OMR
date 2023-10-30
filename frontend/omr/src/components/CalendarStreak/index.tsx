'use client';

import React, { useState } from 'react';

import Image from 'next/image';

import styles from './index.module.scss';
import BeforeLogin from '../BeforeLogin';
import Calendar from '../Calendar';

import { DUMMY_STREAK } from '@/constants/calendar';

const CalendarStreak = () => {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <div className={styles.CalendarStreak}>
      {!isLogin ? (
        <>
          <BeforeLogin />
          <Image
            src="/images/calendar.svg"
            alt="calendar"
            width={276}
            height={256}
          />
        </>
      ) : (
        // dummy data
        <>
          <Calendar streaks={DUMMY_STREAK} />
        </>
      )}
    </div>
  );
};

export default CalendarStreak;

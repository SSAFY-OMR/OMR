'use client';

import React, { useState } from 'react';

import Image from 'next/image';

import styles from './index.module.scss';
import BeforeLogin from '../BeforeLogin';
import Calendar from '../Calendar';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import useStreaks from '@/hooks/useStreaks';
import { userTokenState } from '@/states/auth';

const CalendarStreak = () => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [userToken, setUserToken] = useSSRRecoilState<string>(
    userTokenState,
    '',
  );
  const { res, isLoading, isError } = useStreaks({
    month: currentMonth.getMonth() + 1,
    year: currentMonth.getFullYear(),
    isTriggered: userToken !== '',
  });

  return (
    <div className={styles.CalendarStreak}>
      {userToken ? (
        <>
          <div className={styles.streakMessage}>
            현재{' '}
            <span className={styles.streakDays}>
              {isLoading ? '-' : res.data.currentStreak}일
            </span>{' '}
            연속 OMR 중이에요.
          </div>
          <div className={styles.streakMessage}>
            최장 OMR 기록은{' '}
            <span className={styles.streakDays}>
              {isLoading ? '-' : res.data.longestStreak}일
            </span>
            이에요.
          </div>
          <Calendar
            currentMonth={currentMonth}
            setCurrentMonth={setCurrentMonth}
            streaks={isLoading ? {} : res.data.streaks}
          />
        </>
      ) : (
        <>
          <BeforeLogin />
          <Image
            src="/images/calendar.svg"
            alt="calendar"
            width={276}
            height={256}
          />
        </>
      )}
    </div>
  );
};

export default CalendarStreak;

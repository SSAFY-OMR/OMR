'use client';

import React, { useState } from 'react';

import Image from 'next/image';

import styles from './index.module.scss';
import BeforeLogin from '../BeforeLogin';
import Calendar from '../Calendar';

import type { User } from '@/types/user';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import useStreaks from '@/hooks/useStreaks';
import { userInfoState, userTokenState } from '@/states/auth';

const CalendarStreak = () => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [userInfo, setUserInfo] = useSSRRecoilState<User>(userInfoState, {
    loginId: '',
    nickname: '',
    emoji: '',
  });
  const [userToken, setUserToken] = useSSRRecoilState<string>(
    userTokenState,
    '',
  );
  const {
    data: streaks,
    isLoading,
    isError,
  } = useStreaks({
    month: currentMonth.getMonth() + 1,
    year: currentMonth.getFullYear(),
    isTriggered: userToken !== '',
  });

  return (
    <div className={styles.CalendarStreak}>
      {userToken ? (
        <>
          {userInfo.nickname !== '' && (
            <div className={styles.title}>{userInfo.nickname}님의 기록</div>
          )}
          <div className={styles.streakMessage}>
            현재{' '}
            <span className={styles.streakDays}>
              {isLoading ? '-' : streaks.currentStreak}일
            </span>{' '}
            연속 OMR 중이에요.
          </div>
          <Calendar
            currentMonth={currentMonth}
            setCurrentMonth={setCurrentMonth}
            streaks={isLoading ? {} : streaks.streaks}
            longestStreak={isLoading ? -1 : streaks.longestStreak}
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

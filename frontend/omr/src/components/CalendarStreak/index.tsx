'use client';

import React, { useEffect, useState } from 'react';

import Image from 'next/image';

import styles from './index.module.scss';
import BeforeLogin from '../BeforeLogin';
import Calendar from '../Calendar';
import CalendarLoading from '../Calendar/CalendarLoading';

import type { User } from '@/types/user';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import useStreaks from '@/hooks/useStreaks';
import { getUserInfo } from '@/service/member';
import { userAccessTokenState, userInfoState } from '@/states/auth';

const CalendarStreak = () => {
  const [currentMonth, setCurrentMonth] = useState(new Date());
  const [userAccessToken, setUserAccessToken, loadingUserToken] = useSSRRecoilState<string>(
    userAccessTokenState,
    '',
  );
  const [userInfo, setUserInfo] = useSSRRecoilState<User | undefined>(
    userInfoState,
    undefined,
  );

  useEffect(() => {
    if (userAccessToken) {
      (async () => {
        const res = await getUserInfo();

        if (res?.status === 200) {
          setUserInfo(res.data.data);
        }
      })();
    }
  }, [setUserInfo, userAccessToken]);

  const {
    data: streaks,
    isLoading,
    isError,
  } = useStreaks({
    month: currentMonth.getMonth() + 1,
    year: currentMonth.getFullYear(),
    isTriggered: userAccessToken !== '',
  });

  return (
    <div className={styles.CalendarStreak}>
      {loadingUserToken ? (
        <CalendarLoading />
      ) : (
        <>
          {userAccessToken ? (
            <>
              <div className={styles.title}>
                {userInfo && userInfo.nickname}님의 기록
              </div>
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
        </>
      )}
    </div>
  );
};

export default CalendarStreak;

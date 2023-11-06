'use client';

import React from 'react';

import { addMonths, subMonths } from 'date-fns';
import { NextIcon, PrevIcon } from 'public/icons';

import DateCells from './DateCells';
import styles from './index.module.scss';

import type { Streak } from '@/types/streak';

import { DAYS } from '@/constants/calendar';
import { NEUTRAL_500 } from '@/styles/color';

type CalendarProps = {
  currentMonth: Date;
  setCurrentMonth: React.Dispatch<React.SetStateAction<Date>>;
  streaks: Streak;
  longestStreak: number;
};

const Calendar = ({
  currentMonth,
  setCurrentMonth,
  streaks,
  longestStreak,
}: CalendarProps) => {
  const handleClickPrevMonth = () => {
    setCurrentMonth(subMonths(currentMonth, 1));
  };

  const handleClickNextMonth = () => {
    setCurrentMonth(addMonths(currentMonth, 1));
  };

  return (
    <div className={styles.Calendar}>
      <div className={styles.header}>
        <button onClick={handleClickPrevMonth}>
          <PrevIcon width={20} height={20} fill={NEUTRAL_500} />
        </button>
        <span className={styles.currentMonth}>
          {currentMonth.getFullYear()}년 {currentMonth.getMonth() + 1}월
        </span>
        <button onClick={handleClickNextMonth}>
          <NextIcon width={20} height={20} fill={NEUTRAL_500} />
        </button>
      </div>
      <div className={styles.days}>
        {DAYS.map((day, i) => (
          <div key={i} className={styles.day}>
            {day}
          </div>
        ))}
      </div>
      <div className={styles.body}>
        <DateCells currentMonth={currentMonth} streaks={streaks} />
      </div>
      <div className={styles.longestStreak}>
        최장 기록 :{' '}
        <span className={styles.streakDays}>
          {longestStreak === -1 ? '-' : longestStreak}일
        </span>
      </div>
    </div>
  );
};

export default Calendar;

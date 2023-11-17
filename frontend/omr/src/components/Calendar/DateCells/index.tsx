import React from 'react';
import { useState } from 'react';

import {
  addDays,
  endOfMonth,
  endOfWeek,
  format,
  startOfMonth,
  startOfWeek,
} from 'date-fns';

import styles from './index.module.scss';
import Tooltip from '../Tooltip';

import type { Streak } from '@/types/streak';

import { STREAK_COLORS } from '@/constants/calendar';

type DateCellsProps = {
  currentMonth: Date;
  streaks: Streak;
};

const DateCells = ({ currentMonth, streaks }: DateCellsProps) => {
  const [tooltipContent, setTooltipContent] = useState('');
  const [tooltipPosition, setTooltipPosition] = useState({ left: 0, top: 0 });

  const startDateOfMonth = startOfMonth(currentMonth);
  const endDateOfMonth = endOfMonth(currentMonth);
  const startDateOfFirstWeek = startOfWeek(startDateOfMonth);
  const endDateOfLastWeek = endOfWeek(endDateOfMonth);

  const weeks = [];
  let dates = [];

  let date = startDateOfFirstWeek;

  const setCountColor = (count: number | undefined): string => {
    if (count === undefined) {
      return 'var(--color-neutral-40)';
    }

    for (const colors of STREAK_COLORS) {
      if (count <= colors.count) {
        return colors.color;
      }
    }
    return 'var(--color-blue-900)';
  };

  while (date < endDateOfLastWeek) {
    for (let i = 0; i < 7; i++) {
      const isCurrentMonth = date >= startDateOfMonth && date <= endDateOfMonth;
      const count: number | undefined = streaks[format(date, 'yyyy-MM-dd')];

      let color = '';

      if (isCurrentMonth) {
        color = setCountColor(count);
      }

      const now = format(date, 'MM/dd');

      dates.push(
        <div
          key={date.toISOString()}
          style={{ backgroundColor: `${color}` }}
          className={styles.dateCell}
          onMouseOver={(e) => handleClickDate(e, now, count)}
          onMouseOut={() => setTooltipContent('')}
        ></div>,
      );

      date = addDays(date, 1);
    }
    weeks.push(
      <div className={styles.week} key={date.toISOString()}>
        {dates}
      </div>,
    );
    dates = [];
  }

  const handleClickDate = (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>,
    date: string,
    count: number,
  ) => {
    const rect = e.currentTarget.getBoundingClientRect();
    const left = rect.left + rect.width / 2;
    const top = rect.top;

    if (!count) return;

    setTooltipContent(`${date}  ${count.toString()}문제`);
    setTooltipPosition({ left, top });
  };

  return (
    <div className={styles.DateCells}>
      {weeks}
      {tooltipContent && (
        <Tooltip
          isShown={tooltipContent !== ''}
          content={tooltipContent}
          left={tooltipPosition.left}
          top={tooltipPosition.top - 30}
        />
      )}
    </div>
  );
};

export default DateCells;

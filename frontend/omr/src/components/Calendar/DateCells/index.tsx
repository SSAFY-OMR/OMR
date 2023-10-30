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
      return 'neutral40';
    } else if (count < 3) {
      return 'blue100';
    } else if (count < 5) {
      return 'blue300';
    } else if (count < 7) {
      return 'blue500';
    } else if (count >= 7) {
      return 'blue700';
    }
    return '';
  };

  while (date < endDateOfLastWeek) {
    for (let i = 0; i < 7; i++) {
      const isCurrentMonth = date >= startDateOfMonth && date <= endDateOfMonth;
      const count: number | undefined = streaks[format(date, 'yyyy-MM-dd')];

      let color = '';

      if (isCurrentMonth) {
        color = setCountColor(count);
      }

      dates.push(
        <div
          key={date.toISOString()}
          className={`${styles.dateCell} ${styles[color]}`}
          onMouseOver={(e) => handleClickDate(e, count)}
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
    count: number,
  ) => {
    const rect = e.currentTarget.getBoundingClientRect();
    const left = rect.left + rect.width / 2;
    const top = rect.top;

    if (!count) return;

    setTooltipContent(count.toString());
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

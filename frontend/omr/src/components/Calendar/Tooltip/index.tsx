import React from 'react';

import styles from './index.module.scss';

import Portal from '@/components/UI/Portal';

type TooltipProps = {
  isShown: boolean;
  content: string;
  left: number;
  top: number;
};

const Tooltip = ({ isShown, content, left, top }: TooltipProps) => {
  const style = {
    left: `${left}px`,
    top: `${top}px`,
  };

  return (
    <Portal selector="#portal">
      <div
        className={`${styles.Tooltip} ${isShown ? styles.show : ''}`}
        style={style}
      >
        {content}
      </div>
    </Portal>
  );
};

export default Tooltip;

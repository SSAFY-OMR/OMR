import React from 'react';

import { createPortal } from 'react-dom';

import styles from './index.module.scss';

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

  return createPortal(
    <div
      className={`${styles.Tooltip} ${isShown ? styles.show : ''}`}
      style={style}
    >
      {content}
    </div>,
    document.body,
  );
};

export default Tooltip;

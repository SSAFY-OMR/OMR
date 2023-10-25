import React, { useState } from 'react';

import { ArrowIcon, CheckIcon, CommunityIcon, EditIcon } from 'public/icons';

import styles from './index.module.scss';

import { BLUE_600, WHITE } from '@/styles/color';

type ButtonProps = {
  children: React.ReactNode;
  size: 'small' | 'medium' | 'large';
  color: 'primary' | 'secondary';
  width: 'fitContent' | 'full';
  type: 'complete' | 'arrow' | 'edit' | 'community';
};

const Button = ({ children, size, color, width, type }: ButtonProps) => {
  const [name, setName] = useState('');

  const iconSize = {
    small: '16px',
    medium: '20px',
    large: '24px',
  };

  const iconColor = {
    primary: WHITE,
    secondary: BLUE_600,
  };

  let Icon: any = undefined;
  if (type === 'complete') {
    Icon = (
      <CheckIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (type === 'arrow') {
    Icon = (
      <ArrowIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (type === 'community') {
    Icon = (
      <CommunityIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (type === 'edit') {
    Icon = (
      <EditIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  }

  return (
    <button
      className={`${styles.Button} ${styles[size]} ${styles[color]} ${styles[width]}`}
    >
      <span className={styles.content}>{children}</span>
      <span className={styles.icon}>{Icon && Icon}</span>
    </button>
  );
};

export default Button;

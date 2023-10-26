import React from 'react';

import { ArrowIcon, CheckIcon, CommunityIcon, EditIcon } from 'public/icons';

import styles from './index.module.scss';

import { BLUE_600, WHITE } from '@/styles/color';

type ButtonProps = {
  children: React.ReactNode;
  size: 'small' | 'medium' | 'large';
  color: 'primary' | 'secondary';
  width: 'fitContent' | 'full';
  iconType: 'complete' | 'arrow' | 'edit' | 'community';
  type: 'button' | 'submit';
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
};

const Button = ({
  children,
  size,
  color,
  width,
  iconType,
  type,
  onClick,
}: ButtonProps) => {
  const iconSize = {
    small: 16,
    medium: 20,
    large: 24,
  };

  const iconColor = {
    primary: WHITE,
    secondary: BLUE_600,
  };

  let Icon: any = undefined;
  if (iconType === 'complete') {
    Icon = (
      <CheckIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (iconType === 'arrow') {
    Icon = (
      <ArrowIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (iconType === 'community') {
    Icon = (
      <CommunityIcon
        height={iconSize[size]}
        width={iconSize[size]}
        fill={iconColor[color]}
      />
    );
  } else if (iconType === 'edit') {
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
      type={type}
      className={`${styles.Button} ${styles[size]} ${styles[color]} ${styles[width]}`}
      onClick={onClick}
    >
      <span className={styles.content}>{children}</span>
      <span className={styles.icon}>{Icon && Icon}</span>
    </button>
  );
};

export default Button;

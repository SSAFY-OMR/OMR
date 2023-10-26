import React from 'react';

import { LikeIcon } from 'public/icons';

import styles from './index.module.scss';

import { NEUTRAL_60 } from '@/styles/color';

type LikeButtonProps = {
  onClick?: any;
  isLiked: boolean;
  likeCount: number;
};

const LikeButton = ({ onClick, isLiked, likeCount }: LikeButtonProps) => {
  return (
    <div className={styles.LikeButton}>
      <button onClick={onClick}>
        <LikeIcon
          width={20}
          height={20}
          fill={isLiked ? 'black' : NEUTRAL_60}
        />
      </button>
      <div className={styles.likeCount}>{likeCount}</div>
    </div>
  );
};

export default LikeButton;

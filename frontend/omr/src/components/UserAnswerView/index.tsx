import React from 'react';

import styles from './index.module.scss';
import LikeButton from '../LikeButton';

import type { Answer } from '@/types/question';

type UserAnswerViewProps = {
  answer: Answer;
};

const UserAnswerView = ({ answer }: UserAnswerViewProps) => {
  return (
    <div className={styles.UserAnswerView}>
      <div className={styles.header}>
        <div className={styles.profile}>
          <div className={styles.profileEmojiContainer}>
            <div className={styles.profileEmoji}>{answer.emoji}</div>
          </div>
          <div className={styles.nickname}>{answer.nickname}</div>
        </div>
        {/* dummy data */}
        <LikeButton isLiked={true} likeCount={answer.likeCount} />
      </div>
      <div className={styles.content}>{answer.content}</div>
    </div>
  );
};

export default UserAnswerView;

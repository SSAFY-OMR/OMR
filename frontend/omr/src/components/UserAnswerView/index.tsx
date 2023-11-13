import React, { useState } from 'react';

import { MenuIcon } from 'public/icons';

import styles from './index.module.scss';
import { NEUTRAL_100 } from '../../styles/color';
import LikeButton from '../LikeButton';

import type { Answer } from '@/types/question';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { deleteAnswer } from '@/service/answer';
import { toastMessageState } from '@/states/ui';

type UserAnswerViewProps = {
  isMine: boolean;
  answer: Answer;
  mutateAnswerList: any;
  toggleLike: any;
};

const UserAnswerView = ({
  isMine,
  answer,
  mutateAnswerList,
  toggleLike,
}: UserAnswerViewProps) => {
  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleClickMenuBtn = () => {
    setIsMenuOpen((prev) => !prev);
  };

  const handleClickDeleteBtn = async () => {
    if (!confirm('답변을 삭제합니다.')) return;
    const res = await deleteAnswer(answer.answerId);
    if (res?.status === 200) {
      await mutateAnswerList();
      setToastMessage('답변을 삭제했어요.');
    }
  };

  const handleClickLikeBtn = () => {
    toggleLike(answer.answerId);
  };

  return (
    <div className={`${styles.UserAnswerView} ${isMine && styles.isMine}`}>
      <div className={styles.header}>
        <div className={styles.profile}>
          <div className={styles.profileEmojiContainer}>
            <div className={styles.profileEmoji}>{answer.emoji}</div>
          </div>
          <div className={styles.nickname}>{answer.nickname}</div>
        </div>
        <div className={styles.buttons}>
          <LikeButton
            isLiked={answer.isLiked}
            likeCount={answer.likeCount}
            onClick={handleClickLikeBtn}
          />
          {isMine && (
            <div
              className={`${styles.menu} ${isMenuOpen ? styles.active : ''}`}
            >
              <button
                id="deleteBtn"
                onClick={handleClickMenuBtn}
                className={styles.deleteBtn}
              >
                <MenuIcon width={20} height={20} fill={NEUTRAL_100} />
              </button>
              {isMenuOpen && (
                <div className={styles.deleteWindow}>
                  <button
                    onClick={handleClickDeleteBtn}
                    className={styles.deleteBtn}
                  >
                    삭제
                  </button>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
      <div className={styles.content}>{answer.content}</div>
    </div>
  );
};

export default UserAnswerView;

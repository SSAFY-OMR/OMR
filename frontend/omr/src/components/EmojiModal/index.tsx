'use client';

import React from 'react';

import EmojiPicker from 'emoji-picker-react';

import styles from './index.module.scss';

import type { User } from '@/types/user';
import type { EmojiClickData } from 'emoji-picker-react';

import { updateUserEmoji } from '@/service/member';

type EmojiModalProps = {
  handleEmojiModalClose: () => void;
  setToast: React.Dispatch<React.SetStateAction<string>>;
  setUser: React.Dispatch<React.SetStateAction<User | undefined>>;
};

const EmojiModal = ({
  handleEmojiModalClose,
  setToast,
  setUser,
}: EmojiModalProps) => {
  const handleEmojiPicker = async (emojiClickData: EmojiClickData) => {
    const result = await updateUserEmoji(emojiClickData.emoji);
    if (result) {
      setUser((prev) => {
        if (prev) {
          return {
            ...prev,
            emoji: emojiClickData.emoji,
          };
        }
      });
      setToast('이모지를 업데이트 했어요.');
    } else {
      setToast('이모지 업데이트에 실패했어요. 관리자에게 문의하세요.');
    }
    handleEmojiModalClose();
  };

  return (
    <div className={styles.EmojiModal}>
      <div
        className={`clickable ${styles.modalBackGround}`}
        onClick={handleEmojiModalClose}
      ></div>
      <div className={styles.modalWrapper}>
        <div className={styles.modal}>
          <div className={styles.modalHeader}>
            <span
              className={`clickable ${styles.modalExitButton}`}
              onClick={handleEmojiModalClose}
            >
              X
            </span>
          </div>
          <EmojiPicker width={'100%'} onEmojiClick={handleEmojiPicker} />
        </div>
      </div>
    </div>
  );
};

export default EmojiModal;

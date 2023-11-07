'use client';

import React from 'react';

import EmojiPicker from 'emoji-picker-react';

import { updateUserEmoji } from '@/service/member';

import styles from './index.module.scss';

import type { User } from '@/types/user';
import type { EmojiClickData } from 'emoji-picker-react';



type EmojiModalProps = {
    handleEmojiModalClose: () => void,
    setUser: React.Dispatch<React.SetStateAction<User | undefined>>,
};

const EmojiModal = ({ handleEmojiModalClose, setUser }:EmojiModalProps) => {

  const handleEventClickPropagation = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
    e.stopPropagation();
  }

  const handleEmojiPicker = async (emojiClickData: EmojiClickData, e: MouseEvent) => {
    e.stopPropagation();
    const result = await updateUserEmoji(emojiClickData.emoji);
    if(result) {
      setUser((prev)=>{
        if(prev) {
          return {
            ...prev,
            emoji: emojiClickData.emoji
          }
        }
      });
    }
    handleEmojiModalClose();
  }

  return (
    <div className={styles.EmojiModal} onClick={handleEmojiModalClose}>
      <div className={styles.modalWrapper}>
        <div className={styles.modal} onClick={handleEventClickPropagation}>
          <div className={styles.modalHeader}>
            <span className={styles.modalExitButton} onClick={handleEmojiModalClose}>X</span>
          </div>
          <EmojiPicker width={'100%'} onEmojiClick={handleEmojiPicker}/>
        </div>
      </div>
    </div>
  );
};

export default EmojiModal;
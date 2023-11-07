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

  const handleEmojiPicker = async (emojiClickData: EmojiClickData) => {
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
  }

  return (
    <div className={styles.EmojiModal}>
      <div className={styles.modalBackGround} onClick={handleEmojiModalClose}></div>

      <div className={styles.modalWrapper}>
        <div className={styles.modal}>
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
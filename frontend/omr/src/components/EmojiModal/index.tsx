'use client';

import React, { useState } from 'react';

import EmojiPicker from 'emoji-picker-react';

import { updateUserEmoji } from '@/service/member';

import styles from './index.module.scss';
import Toast from '../UI/Toast';

import type { User } from '@/types/user';
import type { EmojiClickData } from 'emoji-picker-react';


type EmojiModalProps = {
    handleEmojiModalClose: () => void,
    setUser: React.Dispatch<React.SetStateAction<User | undefined>>,
};

const EmojiModal = ({ handleEmojiModalClose, setUser }:EmojiModalProps) => {

  const [toastMessage, setToastMessage] = useState('');
  
  const handleCloseToast = () => {
    setToastMessage('');
    handleEmojiModalClose();
  };

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
      setToastMessage('이모지 변경에 성공하였습니다. 🤗');
    } else {
      setToastMessage('이모지 변경에 실패하였습니다. 관리자에게 문의하세요. 😔');
    }
  }

  return (
    <>
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
    <Toast
      message={toastMessage}
      isShown={toastMessage !== ''}
      onClose={handleCloseToast}
    />
    </>
  );
};

export default EmojiModal;
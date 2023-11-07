'use client';

import React from 'react';

import EmojiPicker from 'emoji-picker-react';

import { updateUserEmoji } from '@/service/member';

import styles from './index.module.scss';

import type { User } from '@/types/user';



type EmojiModalProps = {
    handleEmojiModalClose: () => void,
    setUser: React.Dispatch<React.SetStateAction<User | undefined>>,
};

const EmojiModal = ({ handleEmojiModalClose, setUser }:EmojiModalProps) => {

  return (
    <div className={styles.EmojiModal} onClick={handleEmojiModalClose}>

      <div className={styles.modalWrapper}>
        <div className={styles.modal} onClick={(e)=>{e.stopPropagation()}}>
          <div className={styles.modalHeader}>
            <span className={styles.modalExitButton} onClick={handleEmojiModalClose}>X</span>
          </div>
          <EmojiPicker width={'100%'} onEmojiClick={async (selected, e)=>{
            e.stopPropagation();
            const result = await updateUserEmoji(selected.emoji);
            if(result) {
              setUser((prev)=>{
                if(prev) {
                  return {
                    ...prev,
                    emoji: selected.emoji
                  }
                }
              });
            }
            handleEmojiModalClose();
          }}/>
        </div>
      </div>
    </div>
  );
};

export default EmojiModal;
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
    <div className={styles.emojiSelector} onClick={handleEmojiModalClose}>

      <div className={styles.emojiModalWrapper}>
        <div className={styles.emojiModal} onClick={(e)=>{e.stopPropagation()}}>
          <div className={styles.emojiModalHeader}>
            <span className={styles.emojiModalExit} onClick={handleEmojiModalClose}>X</span>
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
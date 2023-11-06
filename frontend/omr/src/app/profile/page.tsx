'use client';

import React, { useEffect, useState, useRef } from 'react';

import ChangePasswordForm from '@/components/ChangePasswordForm';
import EmojiPicker from 'emoji-picker-react';
import { getUserInfo, updateUserEmoji } from '@/service/member';
import { BLACK } from '@/styles/color';
import { EditIcon } from 'public/icons';

import styles from './index.module.scss';

import type { User } from '@/types/user';

const ProfilePage = () => {
  const [user, setUser] = useState<User | undefined>(undefined);
  const [modalOpen, setModalOpen] = useState<boolean>(false);

  // eslint-disable-next-line no-null/no-null
  const modalRef = useRef<HTMLDivElement>(null);

  const modalOutSideClick = (e:any) => {
    console.log(modalRef.current)
    console.log(e.target)
      if(modalRef.current !== e.target) {
        setModalOpen(false);
      }
  }

  const handleEmojiModalOpen = (e: any) => {
    e.stopPropagation();
    setModalOpen(true);
  }

  const handleEmojiModalClose = () => {
    setModalOpen(false);
  }

  useEffect(() => {
    (async () => {
      const res = await getUserInfo();
      if (res?.status === 200) {
        const user = res.data.data;
        setUser(user);
      }
    })();
  }, []);

  return (
    <div className={styles.ProfilePage} onClick={modalOutSideClick}>
      <div className={styles.profileContainer}>
        <div className={styles.profileContents}>
          {/* 사용자 정보 바인딩  */}
          <div className={styles.profileEmojiContainer} onClick={handleEmojiModalOpen}>
            <div className={styles.emoji}>
              {user?.emoji}
            </div>
            <div className={styles.editButton}>
              <EditIcon width={20} height={20} fill={BLACK} />
            </div>
          </div>

          {/* emoji selector */}
          {
            modalOpen && 
            <div className={styles.emojiSelector} ref={modalRef}>
              <div className={styles.emojiModal}>
                <div className={styles.emojiModalHeader}>
                  <span className={styles.emojiModalExit} onClick={handleEmojiModalClose}>X</span>
                </div>
                <EmojiPicker onEmojiClick={async (selected, e)=>{
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
          }
          
          <div className={styles.nickname}>
            {user?.nickname}
          </div>
          
          <div className={styles.profileContent}>
            <label className={styles.label}>
              아이디
            </label>
            <div className={styles.loginId}>
              {user?.loginId}
            </div>
          </div>
        </div>

        <ChangePasswordForm />
      </div>
    </div>
  );
};

export default ProfilePage;

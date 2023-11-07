'use client';

import React, { useEffect, useState } from 'react';

import ChangePasswordForm from '@/components/ChangePasswordForm';
import EmojiModal from '@/components/EmojiModal/index';
import Toast from '@/components/UI/Toast';
import { getUserInfo } from '@/service/member';
import { BLACK } from '@/styles/color';
import { EditIcon } from 'public/icons';

import styles from './index.module.scss';

import type { User } from '@/types/user';


const ProfilePage = () => {
  const [user, setUser] = useState<User | undefined>(undefined);
  const [modalOpen, setModalOpen] = useState<boolean>(false);
  const [emojiUpdateToastMessage, setEmojiUpdateToastMessage] = useState<string>('');

  const handleEmojiModalOpen = () => {
    setModalOpen(true);
  }

  const handleEmojiModalClose = () => {
    setModalOpen(false);
  }

  const handleCloseEmojiUpdateResultToast = () => {
    setEmojiUpdateToastMessage('');
  };

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
    <>
    {
      modalOpen && <EmojiModal 
        handleEmojiModalClose={handleEmojiModalClose}
        setUser={setUser}
        setToast={setEmojiUpdateToastMessage}
      />
    }
    <div className={styles.ProfilePage}>
      <div className={styles.profileContainer}>
        <div className={styles.profileContents}>
          <div className={styles.profileEmojiContainer} onClick={handleEmojiModalOpen}>
            <div className={styles.emoji}>
              {user?.emoji}
            </div>
            <div className={styles.editButton}>
              <EditIcon width={20} height={20} fill={BLACK} />
            </div>
          </div>

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
      
      <Toast
        message={emojiUpdateToastMessage}
        isShown={emojiUpdateToastMessage !== ''}
        onClose={handleCloseEmojiUpdateResultToast}
      />
    </div>
    </>
  );
};

export default ProfilePage;

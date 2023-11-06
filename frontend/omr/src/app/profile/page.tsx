'use client';

import React, { useEffect, useState } from 'react';

import EmojiModal from '@/components/EmojiModal/index';
import ChangePasswordForm from '@/components/ChangePasswordForm';
import { getUserInfo, updateUserEmoji } from '@/service/member';
import { BLACK } from '@/styles/color';
import { EditIcon } from 'public/icons';

import styles from './index.module.scss';

import type { User } from '@/types/user';

const ProfilePage = () => {
  const [user, setUser] = useState<User | undefined>(undefined);
  const [modalOpen, setModalOpen] = useState<boolean>(false);

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
    <>
    {
      modalOpen && <EmojiModal 
        updateUserEmoji={updateUserEmoji}
        handleEmojiModalClose={handleEmojiModalClose}
        setUser={setUser}
      />
    }
    <div className={styles.ProfilePage}>
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
    </>
  );
};

export default ProfilePage;

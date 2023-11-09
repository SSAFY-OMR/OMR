'use client';

import React, { useEffect, useState } from 'react';

import { useRouter } from 'next/navigation';
import { EditIcon } from 'public/icons';

import styles from './index.module.scss';

import type { User } from '@/types/user';

import ChangePasswordForm from '@/components/ChangePasswordForm';
import EmojiModal from '@/components/EmojiModal/index';
import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { getUserInfo } from '@/service/member';
import {
  userAccessTokenState,
  userInfoState,
  userRefreshTokenState,
} from '@/states/auth';
import { toastMessageState } from '@/states/ui';
import { BLACK } from '@/styles/color';

const ProfilePage = () => {
  const router = useRouter();

  const [user, setUser] = useState<User | undefined>(undefined);
  const [modalOpen, setModalOpen] = useState<boolean>(false);

  const [userAccessToken, setUserAccessToken] = useSSRRecoilState(
    userAccessTokenState,
    '',
  );
  const [userRefreshToken, setUserRefreshToken] = useSSRRecoilState(
    userRefreshTokenState,
    '',
  );
  const [userInfo, setUserInfo] = useSSRRecoilState(userInfoState, {});
  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  useEffect(() => {
    (async () => {
      const res = await getUserInfo();
      if (res?.status === 200) {
        const user = res.data.data;
        setUser(user);
      }
    })();
  }, []);

  const handleEmojiModalOpen = () => {
    setModalOpen(true);
  };

  const handleEmojiModalClose = () => {
    setModalOpen(false);
  };

  const handleClickLogout = async () => {
    if (!confirm('로그아웃 하시겠습니까?')) return;
    localStorage.removeItem('USER');
    setUserAccessToken('');
    setUserRefreshToken('');
    setUserInfo({});

    router.replace('/');
  };

  return (
    <>
      {modalOpen && (
        <EmojiModal
          handleEmojiModalClose={handleEmojiModalClose}
          setUser={setUser}
          setToast={setToastMessage}
        />
      )}
      <div className={styles.ProfilePage}>
        <div className={styles.profileContainer}>
          <div className={styles.profileContents}>
            <div
              className={`${styles.profileEmojiContainer} clickable`}
              onClick={handleEmojiModalOpen}
            >
              <div className={styles.emoji}>{user?.emoji}</div>
              <div className={styles.editButton}>
                <EditIcon width={20} height={20} fill={BLACK} />
              </div>
            </div>

            <div className={styles.nickname}>{user?.nickname}</div>

            <div className={styles.idField}>
              <div className={styles.profileContent}>
                <label className={styles.label}>아이디</label>
                <div className={styles.loginId}>
                  {user?.loginId}
                  <div
                    className={`clickable ${styles.logoutBtn}`}
                    onClick={handleClickLogout}
                  >
                    로그아웃
                  </div>
                </div>
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

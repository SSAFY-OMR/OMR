'use client';

import React from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { usePathname, useRouter } from 'next/navigation';
import {
  BookmarkIcon,
  LoginIcon,
  ProfileIcon,
  SettingIcon,
  SolvedIcon,
} from 'public/icons';

import styles from './index.module.scss';
import Toast from '../Toast';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { userAccessTokenState } from '@/states/auth';
import { toastMessageState } from '@/states/ui';
import { BLACK, BLUE_600 } from '@/styles/color';

const Header = () => {
  const router = useRouter();
  const path = usePathname();

  const [userAccessToken, setUserAccessToken] = useSSRRecoilState(
    userAccessTokenState,
    '',
  );
  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const handleRouting = (path: string) => {
    router.push(path);
  };

  const handleCloseToast = () => {
    setToastMessage('');
  };

  return (
    <div className={styles.Header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image
            src="/images/logo.png"
            priority
            width={77.5}
            height={35.12}
            alt="logo"
          />
        </Link>
      </div>
      <div className={styles.buttons}>
        {userAccessToken ? (
          <>
            <button
              id="bookmarkBtn"
              onClick={() => handleRouting('/myomr/scraped')}
              className={`${styles.bookmarkBtn}`}
            >
              <BookmarkIcon
                width={26}
                height={26}
                fill={path.includes('scraped') ? BLUE_600 : BLACK}
              />
            </button>
            <button
              id="myQuestionBtn"
              onClick={() => handleRouting('/myomr/solved')}
              className={styles.solvedBtn}
            >
              <SolvedIcon
                width={26}
                height={26}
                fill={path.includes('solved') ? BLUE_600 : BLACK}
              />
            </button>
            <button
              id="settingBtn"
              onClick={() => handleRouting('/profile')}
              className={styles.settingBtn}
            >
              <ProfileIcon
                width={26}
                height={26}
                fill={path.includes('profile') ? BLUE_600 : BLACK}
              />
            </button>
          </>
        ) : (
          <button
            id="loginBtn"
            onClick={() => handleRouting('/login')}
            className={styles.loginBtn}
          >
            <LoginIcon
              width={26}
              height={26}
              fill={
                path.includes('login') || path.includes('signup')
                  ? BLUE_600
                  : BLACK
              }
            />
          </button>
        )}
      </div>
      <Toast
        message={toastMessage}
        isShown={toastMessage !== ''}
        onClose={handleCloseToast}
      />
    </div>
  );
};

export default Header;

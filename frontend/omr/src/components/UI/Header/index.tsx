'use client';

import React from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { usePathname, useRouter } from 'next/navigation';
import { BookmarkIcon, ProfileIcon, SettingIcon } from 'public/icons';

import styles from './index.module.scss';
import Toast from '../Toast';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { toastMessageState } from '@/states/ui';
import { BLACK, BLUE_600 } from '@/styles/color';

const Header = () => {
  const router = useRouter();
  const path = usePathname();

  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const handleClickSolved = () => {
    router.push('/myomr/solved');
  };

  const handleClickBookmark = () => {
    router.push('/myomr/scraped');
  };

  const handleClickProfile = () => {
    router.push('/profile');
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
        <button
          id="bookmarkBtn"
          onClick={handleClickBookmark}
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
          onClick={handleClickSolved}
          className={styles.solvedBtn}
        >
          <ProfileIcon
            width={26}
            height={26}
            fill={path.includes('solved') ? BLUE_600 : BLACK}
          />
        </button>
        <button
          id="settingBtn"
          onClick={handleClickProfile}
          className={styles.settingBtn}
        >
          <SettingIcon
            width={26}
            height={26}
            fill={path.includes('profile') ? BLUE_600 : BLACK}
          />
        </button>
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

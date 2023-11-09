'use client';

import React from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { BookmarkIcon, ProfileIcon } from 'public/icons';

import styles from './index.module.scss';
import Toast from '../Toast';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { toastMessageState } from '@/states/ui';
import { BLACK } from '@/styles/color';

const Header = () => {
  const router = useRouter();

  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const handleClickProfile = () => {
    router.push('/profile');
  };

  const handleClickBookmark = () => {
    router.push('/myomr');
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
          className={styles.bookmarkBtn}
        >
          <BookmarkIcon width={26} height={26} fill={BLACK} />
        </button>
        <button
          id="profileBtn"
          onClick={handleClickProfile}
          className={styles.profileBtn}
        >
          <ProfileIcon width={26} height={26} fill={BLACK} />
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

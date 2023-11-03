'use client';

import React, { useEffect } from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { useSession } from 'next-auth/react';
import { BookmarkIcon, ProfileIcon } from 'public/icons';

import styles from './index.module.scss';

import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { userTokenState } from '@/states/auth';
import { BLACK } from '@/styles/color';

const Header = () => {
  const { data: session, status } = useSession();
  const router = useRouter();
  const [userToken, setUserToken] = useSSRRecoilState(userTokenState, '');

  if (status === 'authenticated' && typeof window !== 'undefined') {
    setUserToken(session?.user.accessToken);
  }

  const handleClickProfile = () => {
    if (status === 'authenticated' || userToken) {
      router.push('/profile');
    } else {
      router.push('/login');
    }
  };

  const handleClickBookmark = () => {
    if (status === 'authenticated' || userToken) {
      router.push('/bookmark');
    } else {
      router.push('/login');
    }
  };

  return (
    <div className={styles.Header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image
            src="/images/logo.png"
            layout="responsive"
            width={77.5}
            height={35}
            alt="logo"
          />
        </Link>
      </div>
      <div className={styles.buttons}>
        <button onClick={handleClickBookmark}>
          <BookmarkIcon width={24} height={24} fill={BLACK} />
        </button>
        <button onClick={handleClickProfile}>
          <ProfileIcon width={24} height={24} fill={BLACK} />
        </button>
      </div>
    </div>
  );
};

export default Header;

'use client';

import React from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { useSession } from 'next-auth/react';
import { BookmarkIcon, ProfileIcon } from 'public/icons';

import styles from './index.module.scss';

import { BLACK } from '@/styles/color';

const Header = () => {
  const { status } = useSession();
  const router = useRouter();

  const handleClickProfile = () => {
    if (status === 'authenticated') {
      router.push('/profile');
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
        <button>
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

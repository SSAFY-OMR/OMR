import React from 'react';

import Image from 'next/image';
import Link from 'next/link';
import { BookmarkIcon, ProfileIcon } from 'public/icons';

import styles from './index.module.scss';

import { BLACK } from '@/styles/color';

const Header = () => {
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
        <button>
          <ProfileIcon width={24} height={24} fill={BLACK} />
        </button>
      </div>
    </div>
  );
};

export default Header;

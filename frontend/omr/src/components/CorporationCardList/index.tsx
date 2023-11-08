'use client';

import React from 'react';

import { useRouter } from 'next/navigation';

import styles from './index.module.scss';
import CategoryCardButton from '../CategoryCardButton';

import useCorporationList from '@/hooks/useCorporationList';

const CorporationCardList = () => {
  const { corporations } = useCorporationList();

  const router = useRouter();

  const handleClickViewAll = () => {
    router.push(
      `/questionlist/corporation/${corporations[0].corporationType.name}`,
    );
  };

  const handleClickCategoryCard = (category: string) => {
    router.push(`/questionlist/corporation/${category}`);
  };

  return (
    <div className={styles.CateoryCardList}>
      <div className={styles.header}>
        <div className={styles.title}>회사별 기출 문제</div>
        <div onClick={handleClickViewAll} className={styles.viewAllBtn}>
          전체 보기
        </div>
      </div>
      <div className={styles.list}>
        {corporations &&
          corporations.map((category) => {
            return (
              <CategoryCardButton
                key={category.corporationType.id}
                category={category.corporationType}
                count={category.count}
                onClick={() =>
                  handleClickCategoryCard(category.corporationType.name)
                }
              />
            );
          })}
      </div>
    </div>
  );
};

export default CorporationCardList;

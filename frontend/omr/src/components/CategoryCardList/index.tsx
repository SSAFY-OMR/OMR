'use client';

import React from 'react';

import styles from './index.module.scss';
import CategoryCardButton from '../CategoryCardButton';

import useCategoryList from '@/hooks/useCategoryList';

const CategoryCardList = () => {
  const { categoryList } = useCategoryList();

  return (
    <div className={styles.CateoryCardList}>
      <div className={styles.title}>카테고리별 문제 보기</div>
      <div className={styles.list}>
        {categoryList &&
          categoryList.map((category) => (
            <CategoryCardButton
              key={category.id}
              category={category}
              count={50}
            />
          ))}
      </div>
    </div>
  );
};

export default CategoryCardList;

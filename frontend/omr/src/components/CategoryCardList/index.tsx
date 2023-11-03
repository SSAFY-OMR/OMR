'use client';

import React from 'react';

import styles from './index.module.scss';
import CategoryCardButton from '../CategoryCardButton';

import useCategoryCount from '@/hooks/useCategoryCount';
import useCategoryList from '@/hooks/useCategoryList';

const CategoryCardList = () => {
  const { categoryList } = useCategoryList();
  const { categoryCount } = useCategoryCount();

  return (
    <div className={styles.CateoryCardList}>
      <div className={styles.title}>카테고리별 문제 보기</div>
      <div className={styles.list}>
        {categoryList &&
          categoryCount &&
          categoryList.map((category) => {
            let count = 0;
            for (let i = 0; i < categoryCount.length; i++) {
              if (categoryCount[i].interviewCategory.id === category.id) {
                count = categoryCount[i].count;
              }
            }

            return (
              <CategoryCardButton
                key={category.id}
                category={category}
                count={count}
              />
            );
          })}
      </div>
    </div>
  );
};

export default CategoryCardList;

'use client';

import React, { useRef } from 'react';

import CategoryRadioButton from './CategoryRadioButton';
import styles from './index.module.scss';

import type { CategoryCount, CorporationCount } from '@/types/question';

type CategoryRadioGroupProps = {
  type?: 'category' | 'corporation';
  selectedCategory: string;
  setSelectedCategory: React.Dispatch<React.SetStateAction<string>>;
  categoryCount?: CategoryCount[];
  corporationCount?: CorporationCount[];
};

const CategoryRadioGroup = ({
  type = 'category',
  selectedCategory,
  setSelectedCategory,
  categoryCount,
  corporationCount,
}: CategoryRadioGroupProps) => {
  /*eslint-disable*/
  const containerRef = useRef<HTMLDivElement>(null);

  const handleChangeCategory = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedCategory(e.target.value);
  };

  return (
    <div className={styles.CategoryRadioGroup} ref={containerRef}>
      {type === 'category' && (
        <CategoryRadioButton
          key={0}
          category={{ id: 0, name: 'ALL', description: '전체' }}
          selectedCategory={selectedCategory}
          handleChangeCategory={handleChangeCategory}
        />
      )}
      {type === 'category'
        ? categoryCount &&
          categoryCount.map((category) => {
            if (category.count === 0) return;
            return (
              <CategoryRadioButton
                key={category.interviewCategory.id}
                category={category.interviewCategory}
                selectedCategory={selectedCategory}
                handleChangeCategory={handleChangeCategory}
              />
            );
          })
        : corporationCount &&
          corporationCount.map((corporation) => {
            if (corporation.count === 0) return;
            return (
              <CategoryRadioButton
                key={corporation.corporationType.id}
                category={corporation.corporationType}
                selectedCategory={selectedCategory}
                handleChangeCategory={handleChangeCategory}
              />
            );
          })}
    </div>
  );
};

export default CategoryRadioGroup;

'use client';

import React, { useRef, type WheelEvent } from 'react';

import CategoryRadioButton from './CategoryRadioButton';
import styles from './index.module.scss';

import type { Category } from '@/types/question';

type CategoryRadioGroupProps = {
  type?: 'category' | 'corporation';
  selectedCategory: string;
  setSelectedCategory: React.Dispatch<React.SetStateAction<string>>;
  categoryList: Category[];
};

const CategoryRadioGroup = ({
  type = 'category',
  selectedCategory,
  setSelectedCategory,
  categoryList,
}: CategoryRadioGroupProps) => {
  /*eslint-disable*/
  const containerRef = useRef<HTMLDivElement>(null);

  const handleChangeCategory = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedCategory(e.target.value);
  };

  // const handleScrollEvent = (e: WheelEvent<HTMLDivElement>) => {
  //   if (containerRef.current) {
  //     containerRef.current.scrollLeft += e.deltaY;
  //   }
  // };

  return (
    <div
      className={styles.CategoryRadioGroup}
      // onWheel={handleScrollEvent}
      ref={containerRef}
    >
      {type === 'category' && (
        <CategoryRadioButton
          key={0}
          category={{ id: 0, name: 'ALL', description: '전체' }}
          selectedCategory={selectedCategory}
          handleChangeCategory={handleChangeCategory}
        />
      )}
      {categoryList.map((category) => (
        <CategoryRadioButton
          key={category.id}
          category={category}
          selectedCategory={selectedCategory}
          handleChangeCategory={handleChangeCategory}
        />
      ))}
    </div>
  );
};

export default CategoryRadioGroup;

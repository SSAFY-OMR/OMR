'use client';

import React, { useState } from 'react';

import CategoryRadioButton from './CategoryRadioButton';
import styles from './index.module.scss';

import type { Category } from '@/types/question';

type CategoryRadioGroupProps = {
  categoryList: Category[];
};

const CategoryRadioGroup = ({ categoryList }: CategoryRadioGroupProps) => {
  const [selectedCategory, setSelectedCategory] = useState('ALL');

  const handleChangeCategory = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedCategory(e.target.value);
  };

  return (
    <div className={styles.CategoryRadioGroup}>
      <CategoryRadioButton
        key={0}
        category={{ id: 0, name: 'ALL', description: '전체' }}
        selectedCategory={selectedCategory}
        handleChangeCategory={handleChangeCategory}
      />
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

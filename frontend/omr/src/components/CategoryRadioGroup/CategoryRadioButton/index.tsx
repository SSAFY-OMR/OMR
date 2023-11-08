import React from 'react';

import styles from './index.module.scss';

import type { Category } from '@/types/question';

type CategoryRadioButtonProps = {
  category: Category;
  selectedCategory: string;
  handleChangeCategory: (e: React.ChangeEvent<HTMLInputElement>) => void;
};

const CategoryRadioButton = ({
  category,
  selectedCategory,
  handleChangeCategory,
}: CategoryRadioButtonProps) => {
  return (
    <label
      className={`clickable ${styles.radioBtn} ${
        selectedCategory === category.name ? `${styles.selected}` : ''
      }`}
    >
      <input
        type="radio"
        className={styles.radioInput}
        value={category.name}
        checked={selectedCategory === category.name}
        onChange={(e) => handleChangeCategory(e)}
      />
      <span className={styles.label}>{category.description}</span>
    </label>
  );
};

export default CategoryRadioButton;

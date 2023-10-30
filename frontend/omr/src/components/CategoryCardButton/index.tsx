import React from 'react';

import styles from './index.module.scss';
import Button from '../UI/Button';

import type { Category } from '../../types/question';

type CategoryCardButtonProps = {
  category: Category;
  count: number;
};

const CategoryCardButton = ({ category, count }: CategoryCardButtonProps) => {
  const handleClickCategoryCard = () => {};

  return (
    <div className={styles.CategoryCardButton}>
      <div className={styles.info}>
        <div className={styles.title}>{category.description}</div>
        <div className={styles.count}>총 {count}문제</div>
      </div>
      <Button
        size="small"
        color="secondary"
        iconType="arrow"
        width="fitContent"
        onClick={handleClickCategoryCard}
      >
        문제 풀러 가기
      </Button>
    </div>
  );
};

export default CategoryCardButton;

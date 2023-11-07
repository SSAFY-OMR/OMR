'use client';

import React from 'react';

import styles from './index.module.scss';
import Button from '../UI/Button';

import type { Category } from '../../types/question';

type CategoryCardButtonProps = {
  category: Category;
  count: number;
  onClick: React.MouseEventHandler<HTMLDivElement>;
};

const CategoryCardButton = ({
  category,
  count,
  onClick,
}: CategoryCardButtonProps) => {
  return (
    <div className={styles.CategoryCardButton} onClick={onClick}>
      <div className={styles.info}>
        <div className={styles.title}>{category.description}</div>
        <div className={styles.count}>총 {count}문제</div>
      </div>
      <Button
        size="small"
        color="secondary"
        iconType="arrow"
        width="fitContent"
      >
        문제 풀러 가기
      </Button>
    </div>
  );
};

export default CategoryCardButton;

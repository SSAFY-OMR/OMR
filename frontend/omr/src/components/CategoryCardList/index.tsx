import React from 'react';

import styles from './index.module.scss';
import CategoryCardButton from '../CategoryCardButton';

const CategoryCardList = () => {
  return (
    <div className={styles.CateoryCardList}>
      <div className={styles.title}>카테고리별 문제 보기</div>
      <div className={styles.list}>
        {categoryList.map((category) => (
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

// dummy data
const categoryList = [
  {
    id: 0,
    name: 'NETWORK',
    description: '네트워크',
  },
  {
    id: 1,
    name: 'NETWORK',
    description: '운영체제',
  },
  {
    id: 2,
    name: 'NETWORK',
    description: '데이터베이스',
  },
  {
    id: 3,
    name: 'NETWORK',
    description: '네트워크',
  },
  {
    id: 4,
    name: 'NETWORK',
    description: '네트워크',
  },
  {
    id: 5,
    name: 'NETWORK',
    description: '네트워크',
  },
];

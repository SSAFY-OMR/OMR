import React from 'react';

import styles from './index.module.scss';

type CategoryTagProps = {
  category: string;
};

const CategoryTag = ({ category }: CategoryTagProps) => {
  return (
    <div className={styles.CategoryTag}>
      <span className={styles.category}>{category}</span>
    </div>
  );
};

export default CategoryTag;

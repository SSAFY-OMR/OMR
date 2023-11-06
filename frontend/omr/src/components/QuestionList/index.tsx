'use client';

import React, { useState } from 'react';

import styles from './index.module.scss';
import QuestionListView from './QuestionListView';
import CategoryRadioGroup from '../CategoryRadioGroup';
import Paging from '../UI/Pagination';

import useCategoryList from '@/hooks/useCategoryList';
import useQuestionList from '@/hooks/useQuestionList';

const PAGE_SIZE = 10;

const QuestionList = () => {
  const [selectedCategory, setSelectedCategory] = useState('ALL');
  const [currentPage, setCurrentPage] = useState(1);

  const { categoryList } = useCategoryList();

  const { data } = useQuestionList({
    page: currentPage,
    size: PAGE_SIZE,
    categoryName: selectedCategory === 'ALL' ? undefined : selectedCategory,
  });

  return (
    <div className={styles.QuestionList}>
      <div className={styles.categoryList}>
        {categoryList && (
          <CategoryRadioGroup
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            categoryList={categoryList}
          />
        )}
      </div>
      {data && (
        <>
          <QuestionListView questions={data.questions} />
          <Paging
            page={currentPage}
            setPage={setCurrentPage}
            countPerPage={PAGE_SIZE}
            totalCount={data.totalPageCount}
            pageRange={5}
          />
        </>
      )}
    </div>
  );
};

export default QuestionList;

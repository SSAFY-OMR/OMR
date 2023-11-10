'use client';

import React, { useState } from 'react';
import { useEffect } from 'react';

import styles from './index.module.scss';
import CategoryRadioGroup from '../CategoryRadioGroup';
import QuestionListView from '../QuestionListView';
import Paging from '../UI/Pagination';

import useCategoryCount from '@/hooks/useCategoryCount';
import useQuestionList from '@/hooks/useQuestionList';

const PAGE_SIZE = 5;

const QuestionList = ({ category }: { category: string }) => {
  const [selectedCategory, setSelectedCategory] = useState(category);
  const [currentPage, setCurrentPage] = useState(1);

  const { categoryCount } = useCategoryCount();

  const { data } = useQuestionList({
    page: currentPage,
    size: PAGE_SIZE,
    categoryName: selectedCategory === 'ALL' ? undefined : selectedCategory,
  });

  useEffect(() => {
    setCurrentPage(1);
  }, [selectedCategory]);

  return (
    <div className={styles.QuestionList}>
      <div className={styles.categoryList}>
        {categoryCount && (
          <CategoryRadioGroup
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            categoryCount={categoryCount}
          />
        )}
      </div>
      {data && (
        <>
          {data.questions.length > 0 ? (
            <>
              <QuestionListView
                questions={data.questions}
                listCategory={selectedCategory}
              />
              <Paging
                page={currentPage}
                setPage={setCurrentPage}
                countPerPage={PAGE_SIZE}
                totalCount={data.totalPageCount * PAGE_SIZE}
                pageRange={5}
              />
            </>
          ) : (
            <div className={styles.nodata}>아직 등록된 문제가 없어요.</div>
          )}
        </>
      )}
    </div>
  );
};

export default QuestionList;

'use client';

import React, { useState } from 'react';
import { useEffect } from 'react';

import styles from './index.module.scss';
import CategoryRadioGroup from '../CategoryRadioGroup';
import QuestionListView from '../QuestionListView';
import Paging from '../UI/Pagination';

import useCorporationList from '@/hooks/useCorporationList';
import useCorporationQuuestionList from '@/hooks/useCorporationQuestionList';

const PAGE_SIZE = 5;

const CorporationQuestionList = ({ corporation }: { corporation: string }) => {
  const [selectedCategory, setSelectedCategory] = useState(corporation);
  const [currentPage, setCurrentPage] = useState(1);

  const { corporations } = useCorporationList();

  const { data } = useCorporationQuuestionList({
    page: currentPage,
    size: PAGE_SIZE,
    corporation: selectedCategory,
  });

  useEffect(() => {
    setCurrentPage(1);
  }, [selectedCategory]);

  return (
    <div className={styles.QuestionList}>
      <div className={styles.categoryList}>
        {corporations && (
          <CategoryRadioGroup
            type="corporation"
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            categoryList={corporations.map(
              (corporation) => corporation.corporationType,
            )}
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

export default CorporationQuestionList;

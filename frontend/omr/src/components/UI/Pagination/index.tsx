'use client';

import React from 'react';

import Pagination from 'react-js-pagination';

import './index.scss';

type PagingProps = {
  page: number;
  setPage: React.Dispatch<React.SetStateAction<number>>;
  countPerPage: number;
  totalCount: number;
  pageRange: number;
};

const Paging = ({
  page,
  setPage,
  countPerPage,
  totalCount,
  pageRange,
}: PagingProps) => {
  const handlePageChange = (page: number) => {
    setPage(page);
  };

  return (
    <Pagination
      activePage={page}
      itemsCountPerPage={countPerPage}
      totalItemsCount={totalCount}
      pageRangeDisplayed={pageRange}
      prevPageText={'‹'}
      nextPageText={'›'}
      onChange={handlePageChange}
    />
  );
};

export default Paging;

'use client';

import React, { useState } from 'react';

import Pagination from 'react-js-pagination';

import './index.scss';

type PagingProps = {
  // page: number;
  // setPage: React.Dispatch<React.SetStateAction<number>>;
  countPerPage: number;
  totalCount: number;
  pageRange: number;
};

const Paging = ({ countPerPage, totalCount, pageRange }: PagingProps) => {
  const [page, setPage] = useState(1);

  const handlePageChange = (page: number) => {
    console.log(page);

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

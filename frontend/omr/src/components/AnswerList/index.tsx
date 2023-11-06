import React, { useState } from 'react';

import styles from './index.module.scss';
import Paging from '../UI/Pagination';
import UserAnswerView from '../UserAnswerView';

import useAnswerList from '@/hooks/useAnswerList';

const PAGE_SIZE = 5;

const AnswerList = () => {
  const [currentPage, setCurrentPage] = useState(1);

  // const { data: answers } = useAnswerList({
  //   page: currentPage,
  //   size: PAGE_SIZE,
  // });

  const answers = {
    answers: [
      {
        content: '모르겠는데요',
        likeCount: 30,
        answerId: 1,
        nickname: '배고픈 토끼',
        emoji: '🐰',
      },
      {
        content: '모르겠는데요',
        likeCount: 30,
        answerId: 1,
        nickname: '배고픈 토끼',
        emoji: '🐰',
      },
      {
        content: '모르겠는데요',
        likeCount: 30,
        answerId: 1,
        nickname: '배고픈 토끼',
        emoji: '🐰',
      },
      {
        content: '모르겠는데요',
        likeCount: 30,
        answerId: 1,
        nickname: '배고픈 토끼',
        emoji: '🐰',
      },
      {
        content: '모르겠는데요',
        likeCount: 30,
        answerId: 1,
        nickname: '배고픈 토끼',
        emoji: '🐰',
      },
    ],
    totalPageCount: 10,
  };

  return (
    <div className={styles.AnswerList}>
      {answers.answers.map((answer) => (
        <UserAnswerView key={answer.answerId} answer={answer} />
      ))}
      <Paging
        page={currentPage}
        setPage={setCurrentPage}
        countPerPage={PAGE_SIZE}
        totalCount={answers.totalPageCount}
        pageRange={5}
      />
    </div>
  );
};

export default AnswerList;

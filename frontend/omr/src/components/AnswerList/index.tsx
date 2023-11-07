import React, { useState } from 'react';

import styles from './index.module.scss';
import Paging from '../UI/Pagination';
import UserAnswerView from '../UserAnswerView';

import useAnswerList from '@/hooks/useAnswerList';

const PAGE_SIZE = 5;

type AnswerListProps = {
  questionId: string;
  answerType: 'mine' | 'others';
};

const AnswerList = ({ questionId, answerType }: AnswerListProps) => {
  const [currentPage, setCurrentPage] = useState(1);

  const { data: answers } = useAnswerList({
    questionId: questionId,
    type: answerType,
    page: currentPage,
    size: PAGE_SIZE,
  });

  return (
    <>
      {answers && (
        <>
          {answers.answerResponses.length > 0 ? (
            <div className={styles.AnswerList}>
              {answers.answerResponses.map((answer) => (
                <UserAnswerView key={answer.answerId} answer={answer} />
              ))}
              <Paging
                page={currentPage}
                setPage={setCurrentPage}
                countPerPage={PAGE_SIZE}
                totalCount={answers.totalPageCount * PAGE_SIZE}
                pageRange={5}
              />
            </div>
          ) : (
            <div className={styles.nodata}>아직 입력된 답변이 없어요.</div>
          )}
        </>
      )}
    </>
  );
};

export default AnswerList;

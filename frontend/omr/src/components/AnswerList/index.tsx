import React, { useState } from 'react';

import { useSWRConfig } from 'swr';

import styles from './index.module.scss';
import Paging from '../UI/Pagination';
import UserAnswerView from '../UserAnswerView';

import type { AnswerListResponse } from '@/hooks/useAnswerList';

import useAnswerList from '@/hooks/useAnswerList';
import { useSSRRecoilState } from '@/hooks/useSSRRecoilState';
import { updateLikeAnswer } from '@/service/answer';
import { toastMessageState } from '@/states/ui';

const PAGE_SIZE = 5;

type AnswerListProps = {
  questionId: string;
  answerType: 'mine' | 'others';
};

const AnswerList = ({ questionId, answerType }: AnswerListProps) => {
  const { mutate } = useSWRConfig();

  const [currentPage, setCurrentPage] = useState(1);
  const [toastMessage, setToastMessage] = useSSRRecoilState(
    toastMessageState,
    '',
  );

  const { data: answers, mutate: mutateAnswerList } = useAnswerList({
    questionId: questionId,
    type: answerType,
    page: currentPage,
    size: PAGE_SIZE,
  });

  const toggleLike = async (id: number) => {
    const updatedAnswers: AnswerListResponse = {
      ...answers,
      answerResponses: answers.answerResponses.map((answer) => {
        if (answer.answerId === id) {
          return {
            ...answer,
            likeCount: answer.isLiked
              ? answer.likeCount - 1
              : answer.likeCount + 1,
            isLiked: !answer.isLiked,
          };
        }
        return answer;
      }),
    };

    updatedAnswers.answerResponses.sort((a, b) => b.likeCount - a.likeCount);

    mutateAnswerList(updatedAnswers, false);

    await updateLikeAnswer(id);
  };

  return (
    <>
      {answers && (
        <>
          {answers.answerResponses.length > 0 ? (
            <div className={styles.AnswerList}>
              {answers.answerResponses.map((answer) => (
                <UserAnswerView
                  key={answer.answerId}
                  answer={answer}
                  isMine={answerType === 'mine' ? true : false}
                  mutateAnswerList={mutateAnswerList}
                  toggleLike={toggleLike}
                />
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

import useFetcher from './useFetcher';

import type { Answer } from '@/types/question';
import type { KeyedMutator } from 'swr';

export type AnswerListResponse = {
  answerResponses: Answer[];
  currentPage: number;
  totalPageCount: number;
};

export default function useAnswerList({
  questionId,
  type,
  page,
  size,
}: {
  questionId: string;
  type: 'mine' | 'others';
  page: number;
  size: number;
}): {
  data: AnswerListResponse;
  isLoading: boolean;
  isError: boolean;
  mutate: KeyedMutator<AnswerListResponse>;
} {
  const params = `?page=${page}&size=${size}`;

  const { data, isLoading, isError, mutate } = useFetcher<AnswerListResponse>(
    `/answers/question/${questionId}/${type}`,
    true,
    params,
  );

  return {
    data: data as AnswerListResponse,
    isLoading,
    isError,
    mutate,
  };
}

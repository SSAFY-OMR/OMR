import useFetcher from './useFetcher';

import type { Answer } from '@/types/question';

type AnswerListResponse = {
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
} {
  const params = `?page=${page}&size=${size}`;

  const { data, isLoading, isError } = useFetcher<AnswerListResponse>(
    // TODO: URL 수정 필요
    `/answers/question/${questionId}/${type}`,
    true,
    params,
  );

  return {
    data: data as AnswerListResponse,
    isLoading,
    isError,
  };
}

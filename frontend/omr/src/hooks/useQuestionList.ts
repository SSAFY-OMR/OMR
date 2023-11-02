import useFetcher from './useFetcher';

import type { Question } from '@/types/question';
import type { AxiosResponse } from 'axios';

type QuestionListResponse = {
  questions: Question[];
  currentPage: number;
  totalPageCount: number;
};

export default function useQuestionList({
  page,
  size,
  categoryName,
}: {
  page: number;
  size: number;
  categoryName: string | undefined;
}): {
  res: AxiosResponse<QuestionListResponse>;
  isLoading: boolean;
  isError: boolean;
} {
  const params =
    categoryName === undefined
      ? `?page=${page}&size=${size}`
      : `?page=${page}&size=${size}&category=${categoryName}`;

  const { data, isLoading, isError } = useFetcher<
    AxiosResponse<QuestionListResponse>
  >(`/questions`, params);

  return {
    res: data as AxiosResponse<QuestionListResponse>,
    isLoading,
    isError,
  };
}

import useFetcher from './useFetcher';

import type { Question } from '@/types/question';

type QuestionListResponse = {
  questions: Question[];
  currentPage: number;
  totalPageCount: number;
};

export default function useCorporationQuuestionList({
  page,
  size,
  corporation,
}: {
  page: number;
  size: number;
  corporation: string | undefined;
}): {
  data: QuestionListResponse;
  isLoading: boolean;
  isError: boolean;
} {
  const params = `?page=${page}&size=${size}&corporation=${corporation}`;

  const { data, isLoading, isError } = useFetcher<QuestionListResponse>(
    `/questions/corporation`,
    true,
    params,
  );

  return {
    data: data as QuestionListResponse,
    isLoading,
    isError,
  };
}

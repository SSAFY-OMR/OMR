import useFetcher from './useFetcher';

import type { Category } from '@/types/question';

type CategoryListResponse = {
  metaData: Category[];
};

export default function useCategoryList(): {
  categoryList: Category[];
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } =
    useFetcher<CategoryListResponse>(`/omr-api/category`);

  return {
    categoryList: data?.metaData as Category[],
    isLoading,
    isError,
  };
}

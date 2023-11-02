import useFetcher from './useFetcher';

import type { Category } from '@/types/question';
import type { AxiosResponse } from 'axios';

type CategoryListResponse = {
  metaData: Category[];
};

export default function useCategoryList(): {
  categoryList: Category[];
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } = useFetcher<
    AxiosResponse<CategoryListResponse>
  >(`/category`, '');

  return {
    categoryList: data?.data.metaData as Category[],
    isLoading,
    isError,
  };
}
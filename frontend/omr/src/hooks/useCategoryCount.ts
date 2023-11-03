import useFetcher from './useFetcher';

import type { CategoryCount } from '@/types/question';
import type { AxiosResponse } from 'axios';

type CategoryCountResponse = {
  categoriesCount: CategoryCount[];
};

export default function useCategoryCount(): {
  categoryCount: CategoryCount[];
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } =
    useFetcher<AxiosResponse<CategoryCountResponse>>(`/questions/count`);

  return {
    categoryCount: data?.data.categoriesCount as CategoryCount[],
    isLoading,
    isError,
  };
}

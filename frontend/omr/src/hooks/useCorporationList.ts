import useFetcher from './useFetcher';

import type { CorporationCount } from '@/types/question';

type CategoryCountResponse = {
  corporations: CorporationCount[];
};

export default function useCorporationList(): {
  corporations: CorporationCount[];
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } = useFetcher<CategoryCountResponse>(
    `/omr-api/questions/corporation/count`,
  );

  return {
    corporations: data?.corporations as CorporationCount[],
    isLoading,
    isError,
  };
}

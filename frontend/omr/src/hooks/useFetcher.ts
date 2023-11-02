import useSWR from 'swr';

import { fetcher } from '@/service/utils/fetcher';

export default function useFetcher<T>(url: string, params: string = '') {
  const { data, error, mutate, isLoading } = useSWR<T>(
    [url, params],
    ([url, params]) => fetcher(url, params),
  );

  return {
    data,
    isLoading,
    isError: error,
    mutate,
  };
}

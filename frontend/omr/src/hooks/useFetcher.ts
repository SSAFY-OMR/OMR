import useSWR from 'swr';

import { fetcher } from '@/service/utils/fetcher';

export default function useFetcher<T>(
  url: string,
  isTriggered: boolean = true,
  params: string = '',
) {
  const { data, error, mutate, isLoading } = useSWR<T>(
    // eslint-disable-next-line no-null/no-null
    isTriggered ? [url, params] : null,
    async ([url, params]) => fetcher(url, params),
  );

  return {
    data,
    isLoading,
    isError: error,
    mutate,
  };
}

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
    {
      onErrorRetry: (error, key, config, revalidate, { retryCount }) => {
        // 404에서 재시도 안함
        if (error.status === 404) return;

        // 10번까지만 재시도함
        if (retryCount >= 10) return;
      },
    },
  );

  return {
    data,
    isLoading,
    isError: error,
    mutate,
  };
}

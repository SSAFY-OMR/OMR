import useFetcher from './useFetcher';

import type { Streak } from '@/types/streak';
import type { AxiosResponse } from 'axios';

type StreakResponse = {
  streaks: Streak;
  currentStreak: number;
  longestStreak: number;
};

export default function useStreaks({
  month,
  year,
  isTriggered,
}: {
  month: number;
  year: number;
  isTriggered?: boolean;
}): {
  res: AxiosResponse<StreakResponse>;
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } = useFetcher<
    AxiosResponse<StreakResponse>
  >(`/members/streak/${month}/${year}`, isTriggered);

  return {
    res: data as AxiosResponse<StreakResponse>,
    isLoading,
    isError,
  };
}

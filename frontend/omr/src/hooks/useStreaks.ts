import useFetcher from './useFetcher';

import type { Streak } from '@/types/streak';

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
  data: StreakResponse;
  isLoading: boolean;
  isError: boolean;
} {
  const { data, isLoading, isError } = useFetcher<StreakResponse>(
    `/omr-api/members/streak/${month}/${year}`,
    isTriggered,
  );

  return {
    data: data as StreakResponse,
    isLoading,
    isError,
  };
}

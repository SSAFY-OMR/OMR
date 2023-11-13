import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';
import type { Question } from '@/types/question';

export const getDailyQuestion = async () => {
  try {
    const res =
      await axiosInstance.get<APIResponse<Question>>(`/omr-api/questions/daily`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const updateScrap = async (questionId: string) => {
  try {
    const res = await axiosInstance.post<
      APIResponse<{
        isScrapped: boolean;
      }>
    >(`/omr-api/questions/${questionId}/scrap`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

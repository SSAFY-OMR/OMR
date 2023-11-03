import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';
import type { Question } from '@/types/question';

export const getDailyQuestion = async () => {
  try {
    const res =
      await axiosInstance.get<APIResponse<Question>>(`questions/daily`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const getQuestionDetail = async (questionId: string) => {
  try {
    const res = await axiosInstance.get<APIResponse<Question>>(
      `questions/${questionId}`,
    );

    return res;
  } catch (e) {
    console.error(e);
  }
};

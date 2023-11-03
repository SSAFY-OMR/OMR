import { axiosInstance } from './utils';

export const getDailyQuestion = async () => {
  const res = await axiosInstance.get(`questions/daily`);

  return res;
};

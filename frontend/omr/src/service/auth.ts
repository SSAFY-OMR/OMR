import { axiosInstance } from './utils';

export const login = async (user: { loginId: string; password: string }) => {
  const res = await axiosInstance.post(`/login`, user, {
    headers: {
      'Content-Type': 'application/json',
    },
  });

  return res;
};

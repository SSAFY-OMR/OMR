import { authInstance, defaultInstance } from '.';

export const login = async (user: { loginId: string; password: string }) => {
  const res = await defaultInstance.post(`/login`, user, {
    headers: {
      'Content-Type': 'application/json',
    },
  });

  return res;
};

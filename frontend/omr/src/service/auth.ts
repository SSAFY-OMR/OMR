import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';

export const login = async (user: { loginId: string; password: string }) => {
  try {
    const res = await axiosInstance.post<
      APIResponse<{ accessToken: string; refreshToken: string }>
    >(`/login`, user, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return res;
  } catch (e) {
    console.error(e);
  }
};

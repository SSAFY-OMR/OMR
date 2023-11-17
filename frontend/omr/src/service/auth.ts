import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';

export const login = async (user: { loginId: string; password: string }) => {
  try {
    const res = await axiosInstance.post<
      APIResponse<{ accessToken: string; refreshToken: string }>
    >(`/omr-api/login`, user, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return res;
  } catch (e) {
    console.error(e);
  }
};

export const logout = async () => {
  try {
    const res = await axiosInstance.post(`/omr-api/logout`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const reissue = async (refreshToken: string) => {
  try {
    const res = await axiosInstance.post<
      APIResponse<{ accessToken: string; refreshToken: string }>
    >(
      `/omr-api/reissue`,
      {},
      { headers: { 'Refresh-Token': `Bearer ${refreshToken}` } },
    );

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const getExistence = async (id: string) => {
  try {
    const res = await axiosInstance.get<APIResponse<{ isExist: boolean }>>(
      `/omr-api/existence?loginId=${id}`,
    );

    return res;
  } catch (e) {
    console.error(e);
  }
};

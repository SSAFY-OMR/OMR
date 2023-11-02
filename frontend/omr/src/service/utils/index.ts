import axios from 'axios';
import { getSession } from 'next-auth/react';

export const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

axiosInstance.interceptors.request.use(
  async (request) => {
    const session = await getSession();

    if (session) {
      const accessToken = session.user.accessToken;
      request.headers['Authorization'] = `Bearer ${accessToken}`;
      axiosInstance.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${accessToken}`;
    }

    return request;
  },
  (error) => {
    return Promise.reject(error);
  },
);

// TODO: 토큰 만료로 권한 에러 발생 시 리이슈 해줘야 함

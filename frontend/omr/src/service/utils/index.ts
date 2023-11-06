import axios from 'axios';

import { userTokenState } from '../../states/auth';
import { reissue } from '../auth';

import { isTokenExpired } from '@/utils/jwtProvider';

export const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

axiosInstance.interceptors.request.use(
  async (request) => {
    if (typeof window !== 'undefined' && localStorage.getItem('USER')) {
      let user = JSON.parse(localStorage.getItem('USER')!);
      let accessToken = user.userTokenState;

      if (accessToken && isTokenExpired(accessToken)) {
        console.log('토큰 만료');

        const res = await reissue();
        if (res?.status === 200) {
          accessToken = res.data.data.accessToken;
          user = {
            ...user,
            userTokenState: accessToken,
          };
          localStorage.setItem('USER', user);
        }
      }

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

// 응답 인터셉터
axiosInstance.interceptors.response.use(
  (response) => {
    // 응답 200번대 status일 때 응답 성공 직전 호출
    // 3. 이 작업 이후 .then()으로 이어진다
    return response;
  },
  (error) => {
    // 응답 200번대가 아닌 status일 때 응답 에러 직전 호출
    // 4. 이 작업 이후 .catch()로 이어진다
    return Promise.reject(error);
  },
);

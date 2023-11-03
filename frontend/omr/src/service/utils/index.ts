import axios from 'axios';

export const axiosInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

axiosInstance.interceptors.request.use(
  async (request) => {
    if (typeof window !== 'undefined' && localStorage.getItem('USER')) {
      const accessToken = JSON.parse(
        localStorage.getItem('USER')!,
      ).userTokenState;

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

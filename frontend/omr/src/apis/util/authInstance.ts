import axios, { AxiosError } from 'axios';
import { isExpired } from './jwtProvider';

export const authInstance = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
});

authInstance.interceptors.request.use(

  async (request) => {
    if (!localStorage.getItem('accessToken')) {
        return Promise.reject();
    }

    const accessToken = localStorage
      .getItem('accessToken')!
      .replaceAll('"', '');

    if (isExpired(accessToken)) {

        const refreshToken = localStorage
        .getItem('refreshToken')!
        .replaceAll('"', '');

      try {
        const { data } = await axios.post(
          `${process.env.REACT_APP_API_URL}/reissue`,
          {
            headers: {
              'Refresh-Token': `Bearer ${refreshToken}`,
              'Content-Type': 'application/json',
            },
          }
        );

        const newAccessToken = data.data.accessToken;

        if (request.headers) {
          request.headers['Authorization'] = `Bearer ${newAccessToken}`;
          authInstance.defaults.headers.common[
            'Authorization'
          ] = `Bearer ${newAccessToken}`;
        }

        localStorage.setItem('accessToken', newAccessToken);
      } catch (e) {
        authInstance.defaults.headers.common = {};

        throw new AxiosError('토큰이 만료되었습니다.');
      }
    } else {
      request.headers['Authorization'] = `Bearer ${accessToken}`;
      authInstance.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${accessToken}`;
    }
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);


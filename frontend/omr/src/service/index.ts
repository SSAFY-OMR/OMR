import axios from 'axios';
import { getSession } from 'next-auth/react';

export const defaultInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

export const authInstance = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

authInstance.interceptors.request.use(
  async (request) => {
    const session = await getSession();

    if (session) {
      const accessToken = session.user.accessToken;
      request.headers['Authorization'] = `Bearer ${accessToken}`;
      authInstance.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${accessToken}`;
    }

    return request;
  },
  (error) => {
    return Promise.reject(error);
  },
);

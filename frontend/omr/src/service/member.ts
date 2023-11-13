import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';
import type { User } from '@/types/user';

export const getUserInfo = async () => {
  try {
    const res =
      await axiosInstance.get<APIResponse<User>>(`/omr-api/members/my-profile`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const signUp = async (user: {
  loginId: string;
  password: string;
  emoji: string;
}) => {
  try {
    const res = await axiosInstance.post(`/omr-api/members/signup`, user, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const updateUserEmoji = async (emoji: string) => {
  try {
    const res = 
      await axiosInstance.patch<APIResponse<string>>(`/omr-api/members/emoji`, {
        emoji
      });

    return res;
  } catch(e) {
    console.log(e);
  }
}

export const updateUserPassword = async (password: string) => {
  try {
    const res =
      await axiosInstance.post<APIResponse<any>>(`/omr-api/members/password`, {
        password
      });

    return res;
  } catch(e) {
    console.log(e);
  }
}
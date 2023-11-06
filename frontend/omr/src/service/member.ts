import { axiosInstance } from './utils';

import type { APIResponse } from '@/interface/response';
import type { User } from '@/types/user';

export const getUserInfo = async () => {
  try {
    const res =
      await axiosInstance.get<APIResponse<User>>(`members/my-profile`);

    return res;
  } catch (e) {
    console.error(e);
  }
};

<<<<<<< HEAD
export const signUp = async (user: {
  loginId: string;
  password: string;
  emoji: string;
}) => {
  try {
    const res = await axiosInstance.post(`members/signup`, user, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    return res;
  } catch (e) {
    console.error(e);
  }
};
=======
export const updateUserEmoji = async (emoji: string) => {
  try {
    const res = 
      await axiosInstance.patch<APIResponse<string>>(`members/emoji`, {
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
      await axiosInstance.post<APIResponse<any>>(`members/password`, {
        password
      });

    return res;
  } catch(e) {
    console.log(e);
  }
}
>>>>>>> 12b9f1c (feat: 사용자 패스워드 form validation 문구 추가 및 api 연동)

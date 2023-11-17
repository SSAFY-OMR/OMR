import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

import type { User } from '@/types/user';

const localStorage =
  typeof window !== `undefined` ? window.localStorage : undefined;

const { persistAtom } = recoilPersist({
  key: 'USER', // 고유한 key 값
  storage: localStorage,
});

export const userAccessTokenState = atom<string>({
  key: 'userAccessTokenState',
  default: '',
  effects: [persistAtom],
});

export const userRefreshTokenState = atom<string>({
  key: 'userRefreshTokenState',
  default: '',
  effects: [persistAtom],
});

export const userInfoState = atom<User>({
  key: 'userInfoState',
  default: { loginId: '', nickname: '', emoji: '' },
  effects: [persistAtom],
});

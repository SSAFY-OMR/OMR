import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
  key: 'USER', // 고유한 key 값
  storage: localStorage,
});

export const userTokenState = atom({
  key: 'userTokenState',
  default: '',
  effects: [persistAtom],
});

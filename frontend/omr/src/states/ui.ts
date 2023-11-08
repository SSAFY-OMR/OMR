import { atom } from 'recoil';

export const toastMessageState = atom<string>({
  key: 'toastMessageState',
  default: '',
});

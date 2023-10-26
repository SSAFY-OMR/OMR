import type { User } from './user';

export type Question = {
  category: string;
  content: string;
  isScrapped: boolean;
  answer?: string;
};

export type Answer = {
  user: User;
  content: string;
  likeCount: number;
  isLiked: boolean;
};

import type { User } from './user';

export type Question = {
  category: Category;
  content: string;
  isScrapped: boolean;
  answer?: string;
};

export type Category = {
  id: number;
  name: string;
  description: string;
}

export type Answer = {
  user: User;
  content: string;
  likeCount: number;
  isLiked: boolean;
};

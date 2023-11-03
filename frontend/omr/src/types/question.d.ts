export type Question = {
  questionId: number;
  category: Category;
  content: string;
  isScrapped?: boolean;
  answer?: string;
};

export type Category = {
  id: number;
  name: string;
  description: string;
};

export type Answer = {
  answerId: number;
  nickname: string;
  emoji: string;
  content: string;
  likeCount: number;
};

export type CategoryCount = {
  interviewCategory: Category;
  count: number;
};

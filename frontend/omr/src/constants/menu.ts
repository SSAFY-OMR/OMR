export type AnswerTabItem = {
  menuType: 'mine' | 'others' | 'chat';
  title: string;
};

export const answerTabMenuList: AnswerTabItem[] = [
  {
    menuType: 'mine',
    title: '나의 답변',
  },
  {
    menuType: 'others',
    title: '다른 사람 답변',
  },
  {
    menuType: 'chat',
    title: '챗봇 답변',
  },
];

export type MyOmrTabItem = {
  menuType: 'scraped' | 'solved';
  title: string;
};

export const myOmrTabMenuList: MyOmrTabItem[] = [
  {
    menuType: 'scraped',
    title: '스크랩한 문제',
  },
  {
    menuType: 'solved',
    title: '내가 푼 문제',
  },
];

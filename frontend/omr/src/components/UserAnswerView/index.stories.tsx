import type { Meta, StoryObj } from '@storybook/react';

import UserAnswerView from '.';

const meta: Meta<typeof UserAnswerView> = {
  component: UserAnswerView,
  title: 'Components/UserAnswerView',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof UserAnswerView>;

export const Default: Story = {
  args: {
    answer: {
      user: { id: '', emoji: '🐰', nickname: '배고픈 토끼' },
      content:
        'OSI(Open Systems Interconnection) 모델은 네트워크 통신을 설명하고 표준화하기 위해 개발된 개념적인 모델입니다. 이 모델은 7개의 계층으로 나뉘어 있으며, 각 계층은 네트워크 통신에서 특정 기능을 담당합니다.',
      likeCount: 30,
      isLiked: true,
    },
  },
};

export const Short: Story = {
  args: {
    answer: {
      user: { id: '', emoji: '🐰', nickname: '배고픈 토끼' },
      content: '모르겠는데요',
      likeCount: 2,
      isLiked: false,
    },
  },
};

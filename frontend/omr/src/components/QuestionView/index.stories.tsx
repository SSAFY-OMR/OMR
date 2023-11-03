import type { Meta, StoryObj } from '@storybook/react';

import QuestionView from '.';

const meta: Meta<typeof QuestionView> = {
  component: QuestionView,
  title: 'Components/QuestionView',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof QuestionView>;

export const Default: Story = {
  args: {
    question: {
      questionId: 1,
      category: { id: 3, name: 'NETWORK', description: '네트워크' },
      content: 'OSI 7계층에 대해 설명해보세요.',
      isScrapped: false,
    },
    type: 'questionView',
  },
};

export const ListItem: Story = {
  args: {
    question: {
      questionId: 2,
      category: { id: 3, name: 'NETWORK', description: '네트워크' },
      content: 'OSI 7계층에 대해 설명해보세요.',
      isScrapped: false,
    },
    type: 'listItem',
  },
};

export const LongQuestion: Story = {
  args: {
    question: {
      questionId: 3,
      category: { id: 3, name: 'NETWORK', description: '네트워크' },
      content:
        'OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.',
      isScrapped: true,
    },
    type: 'questionView',
  },
};

export const LongQuestionListItem: Story = {
  args: {
    question: {
      questionId: 1,
      category: { id: 3, name: 'NETWORK', description: '네트워크' },
      content:
        'OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.OSI 7계층에 대해 설명해보세요.',
      isScrapped: false,
    },
    type: 'listItem',
  },
};

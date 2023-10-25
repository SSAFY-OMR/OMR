import type { Meta, StoryObj } from '@storybook/react';

import FeedbackMessage from '.';

const meta: Meta<typeof FeedbackMessage> = {
  component: FeedbackMessage,
  title: 'Components/FeedbackMessage',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof FeedbackMessage>;

export const Error: Story = {
  args: {
    children: '비밀번호가 일치하지 않습니다.',
    type: 'error',
  },
};

export const Success: Story = {
  args: {
    children: '비밀번호가 일치합니다.',
    type: 'success',
  },
};

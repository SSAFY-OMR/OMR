import type { Meta, StoryObj } from '@storybook/react';

import Toast from '.';

const meta: Meta<typeof Toast> = {
  component: Toast,
  title: 'Components/common/Toast',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Toast>;

export const Default: Story = {
  args: {
    isShown: false,
    message: '문제를 보관했어요.',
    onClose: () => {
      console.log('닫기');
    },
  },
};

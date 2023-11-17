import type { Meta, StoryObj } from '@storybook/react';

import Tooltip from '.';

const meta: Meta<typeof Tooltip> = {
  component: Tooltip,
  title: 'Components/Tooltip',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Tooltip>;

export const Default: Story = {
  args: {
    isShown: true,
    content: '3',
    left: 100,
    top: 100,
  },
};

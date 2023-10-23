import type { Meta, StoryObj } from '@storybook/react';

import CategoryTag from '.';

const meta: Meta<typeof CategoryTag> = {
  component: CategoryTag,
  title: 'Components/CategoryTag',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof CategoryTag>;

export const Default: Story = {
  args: {
    category: '네트워크',
  },
};

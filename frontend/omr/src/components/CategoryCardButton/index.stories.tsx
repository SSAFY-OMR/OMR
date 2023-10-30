import type { Meta, StoryObj } from '@storybook/react';

import CategoryCardButton from '.';

const meta: Meta<typeof CategoryCardButton> = {
  component: CategoryCardButton,
  title: 'Components/CategoryCardButton',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof CategoryCardButton>;

export const Editable: Story = {
  args: {
    category: {
      id: 0,
      name: 'NETWORK',
      description: '네트워크',
    },
    count: 50,
  },
};

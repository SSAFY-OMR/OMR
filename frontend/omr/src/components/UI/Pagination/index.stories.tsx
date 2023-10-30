import type { Meta, StoryObj } from '@storybook/react';

import Paging from '.';

const meta: Meta<typeof Paging> = {
  component: Paging,
  title: 'Components/UI/Paging',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Paging>;

export const Default: Story = {
  args: {
    countPerPage: 5,
    totalCount: 54,
    pageRange: 5,
  },
};

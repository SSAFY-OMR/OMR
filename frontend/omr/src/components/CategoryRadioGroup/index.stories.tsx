import type { Meta, StoryObj } from '@storybook/react';

import CategoryRadioGroup from '.';

import { CATEGORY } from '@/constants/category';

const meta: Meta<typeof CategoryRadioGroup> = {
  component: CategoryRadioGroup,
  title: 'Components/CategoryRadioGroup',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof CategoryRadioGroup>;

export const Default: Story = {
  args: {
    categoryList: CATEGORY,
  },
};

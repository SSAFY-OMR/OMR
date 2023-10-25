import type { Meta, StoryObj } from '@storybook/react';

import ScrapButton from '.';

const meta: Meta<typeof ScrapButton> = {
  component: ScrapButton,
  title: 'Components/ScrapButton',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof ScrapButton>;

export const Default: Story = {
  args: {
    isScrapped: false,
  },
};

export const Scrapped: Story = {
  args: {
    isScrapped: true,
  },
};

import type { Meta, StoryObj } from '@storybook/react';

import LikeButton from '.';

const meta: Meta<typeof LikeButton> = {
  component: LikeButton,
  title: 'Components/LikeButton',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof LikeButton>;

export const Default: Story = {
  args: {
    isLiked: false,
    likeCount: 301,
  },
};

export const Liked: Story = {
  args: {
    isLiked: true,
    likeCount: 302,
  },
};

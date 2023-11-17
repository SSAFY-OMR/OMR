import type { Meta, StoryObj } from '@storybook/react';

import EmojiModal from '.';

const meta: Meta<typeof EmojiModal> = {
  component: EmojiModal,
  title: 'Components/EmojiModal',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof EmojiModal>;

export const Default: Story = {
    args: {
    },
  };
import type { Meta, StoryObj } from '@storybook/react';

import ChangePasswordForm from '.';

const meta: Meta<typeof ChangePasswordForm> = {
  component: ChangePasswordForm,
  title: 'Components/ChangePasswordForm',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof ChangePasswordForm>;

export const Default: Story = {
  args: {
  },
};
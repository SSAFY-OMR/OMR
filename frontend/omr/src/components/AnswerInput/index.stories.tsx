import type { Meta, StoryObj } from '@storybook/react';

import AnswerInput from '.';

const meta: Meta<typeof AnswerInput> = {
  component: AnswerInput,
  title: 'Components/AnswerInput',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof AnswerInput>;

export const Default: Story = {
  args: {},
};

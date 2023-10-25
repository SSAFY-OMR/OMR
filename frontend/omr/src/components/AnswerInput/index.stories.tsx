import type { Meta, StoryObj } from '@storybook/react';

import AnswerInput from '.';

const meta: Meta<typeof AnswerInput> = {
  component: AnswerInput,
  title: 'Components/AnswerInput',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof AnswerInput>;

export const Editable: Story = {
  args: {
    type: 'edit',
  },
};

export const ReadOnly: Story = {
  args: {
    type: 'read',
    content:
      'OSI(Open Systems Interconnection) 모델은 네트워크 통신을 설명하고 표준화하기 위해 개발된 개념적인 모델입니다. 이 모델은 7개의 계층으로 나뉘어 있으며, 각 계층은 네트워크 통신에서 특정 기능을 담당합니다.',
  },
};

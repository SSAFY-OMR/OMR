import type { Meta, StoryObj } from '@storybook/react';

import Button from '.';

const meta: Meta<typeof Button> = {
  component: Button,
  title: 'Components/common/Button',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof Button>;

export const Edit: Story = {
  args: {
    children: '수정',
    size: 'small',
    color: 'primary',
    width: 'fitContent',
    iconType: 'edit',
  },
};

export const Complete: Story = {
  args: {
    children: '완료',
    size: 'medium',
    color: 'primary',
    width: 'fitContent',
    iconType: 'complete',
  },
};

export const Community: Story = {
  args: {
    children: '다른 사람 답변 보기',
    size: 'medium',
    color: 'primary',
    width: 'fitContent',
    iconType: 'community',
  },
};

export const Secondary: Story = {
  args: {
    children: '다음 문제 풀기',
    size: 'medium',
    color: 'secondary',
    width: 'fitContent',
    iconType: 'arrow',
  },
};

export const Full: Story = {
  args: {
    children: '회원가입',
    size: 'medium',
    color: 'secondary',
    width: 'full',
    iconType: 'complete',
  },
};

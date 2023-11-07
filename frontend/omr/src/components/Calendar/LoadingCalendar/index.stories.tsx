import type { Meta, StoryObj } from '@storybook/react';

import LoadingCalendar from '.';

const meta: Meta<typeof LoadingCalendar> = {
  component: LoadingCalendar,
  title: 'Components/Calendar/LoadingCalendar',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof LoadingCalendar>;

export const Default: Story = {
  args: {},
};

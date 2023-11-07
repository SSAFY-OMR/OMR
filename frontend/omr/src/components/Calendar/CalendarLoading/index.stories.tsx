import type { Meta, StoryObj } from '@storybook/react';

import CalendarLoading from '.';

const meta: Meta<typeof CalendarLoading> = {
  component: CalendarLoading,
  title: 'Components/Calendar/LoadingCalendar',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof CalendarLoading>;

export const Default: Story = {
  args: {},
};

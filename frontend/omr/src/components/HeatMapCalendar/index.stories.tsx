import type { Meta, StoryObj } from '@storybook/react';

import HeatMapCalendar from '.';

const meta: Meta<typeof HeatMapCalendar> = {
  component: HeatMapCalendar,
  title: 'Components/HeatMapCalendar',
  tags: ['autodocs'],
};

export default meta;

type Story = StoryObj<typeof HeatMapCalendar>;

export const Default: Story = {
  args: {
    value: [
      { date: '2023/08/11', count: 2 },
      { date: '2023/08/12', count: 10 },
      { date: '2023/09/01', count: 5 },
      { date: '2023/09/02', count: 6 },
      { date: '2023/10/03', count: 1 },
      { date: '2023/10/05', count: 2 },
      { date: '2023/10/06', count: 3 },
      { date: '2023/10/07', count: 4 },
      { date: '2023/10/08', count: 5 },
      { date: '2023/10/09', count: 6 },
      { date: '2023/10/10', count: 7 },
      { date: '2023/10/12', count: 8 },
      { date: '2023/10/13', count: 9 },
      { date: '2023/10/18', count: 11 },
      { date: '2023/10/22', count: 12 },
    ],
  },
};

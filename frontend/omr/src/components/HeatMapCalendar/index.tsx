import React from 'react';

import HeatMap from '@uiw/react-heat-map';
import Tooltip from '@uiw/react-tooltip';

import type { RectProps } from '@uiw/react-heat-map/lib/Rect';

import {
  BLUE_100,
  BLUE_300,
  BLUE_500,
  BLUE_700,
  NEUTRAL_40,
} from '@/styles/color';

type HeatMapCalendarProps = {
  value: { date: string; count: number }[];
};

const HeatMapCalendar = ({ value }: HeatMapCalendarProps) => {
  const now = new Date();

  return (
    <HeatMap
      value={value}
      width={120}
      startDate={new Date(now.setDate(1))}
      endDate={new Date(now.getFullYear(), now.getMonth() + 1, -1)}
      legendRender={(props: RectProps) => (
        <rect {...props} y={Number(props.y) + 10} rx={2} />
      )}
      rectProps={{
        rx: 2,
      }}
      rectRender={(props, data) => {
        if (!data.count) return <rect {...props} />;
        return (
          <Tooltip placement="top" content={`${data.count}`}>
            <rect {...props} />
          </Tooltip>
        );
      }}
      rectSize={20}
      panelColors={{
        0: NEUTRAL_40,
        2: BLUE_100,
        4: BLUE_300,
        6: BLUE_500,
        8: BLUE_700,
      }}
    />
  );
};

export default HeatMapCalendar;

import React from 'react';

import type { Preview } from '@storybook/react';
import { INITIAL_VIEWPORTS } from '@storybook/addon-viewport';

import '@/app/global.scss';

const preview: Preview = {
  parameters: {
    viewport: { viewports: INITIAL_VIEWPORTS, defaultViewport: 'iPhone 12' },
    actions: { argTypesRegex: '^on[A-Z].*' },
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
    backgrounds: {
      default: 'omr',
      values: [
        {
          name: 'omr',
          value: '#ebedf0',
        },
      ],
    },
  },
  decorators: [
    (Story) => (
      <div style={{ minHeight: '300px', padding: '10px' }}>
        {/* 👇 Decorators in Storybook also accept a function. Replace <Story/> with Story() to enable it  */}
        <Story />
      </div>
    ),
  ],
};

export default preview;

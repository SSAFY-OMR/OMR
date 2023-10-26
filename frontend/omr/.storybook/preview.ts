import type { Preview } from '@storybook/react';

import '../src/styles/global.scss';

const preview: Preview = {
  parameters: {
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
};

export default preview;

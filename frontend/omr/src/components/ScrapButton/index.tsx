import React from 'react';

import { BookmarkIcon } from 'public/icons';

import { BLUE_600, NEUTRAL_60 } from '@/styles/color';

type ScrapButtonProps = {
  isScrapped: boolean;
};

const ScrapButton = ({ isScrapped }: ScrapButtonProps) => {
  return (
    <button>
      {isScrapped ? (
        <BookmarkIcon width={24} height={24} fill={BLUE_600} />
      ) : (
        <BookmarkIcon width={24} height={24} fill={NEUTRAL_60} />
      )}
    </button>
  );
};

export default ScrapButton;

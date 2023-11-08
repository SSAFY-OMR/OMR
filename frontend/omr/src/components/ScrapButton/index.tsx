import React from 'react';

import { BookmarkIcon } from 'public/icons';

import { BLUE_600, NEUTRAL_60 } from '@/styles/color';

type ScrapButtonProps = {
  isScrapped: boolean;
  onClick: () => void;
};

const ScrapButton = ({ isScrapped, onClick }: ScrapButtonProps) => {
  return (
    <button onClick={onClick} id="scrapBtn">
      {isScrapped ? (
        <BookmarkIcon width={24} height={24} fill={BLUE_600} />
      ) : (
        <BookmarkIcon width={24} height={24} fill={NEUTRAL_60} />
      )}
    </button>
  );
};

export default ScrapButton;

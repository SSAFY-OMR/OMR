import React from 'react';

import CorporationQuestionList from '@/components/CorporationQuestionList';

const CorporationQuestionListPage = ({
  params,
}: {
  params: { corporation: string };
}) => {
  const corporation = params.corporation;

  return (
    <div>
      <CorporationQuestionList corporation={corporation} />
    </div>
  );
};

export default CorporationQuestionListPage;

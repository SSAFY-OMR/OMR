import React from 'react';

import QuestionList from '@/components/QuestionList';

const CategoryQuestionListPage = ({ params }: { params: { category: string } }) => {
  const category = params.category;

  return (
    <div>
      <QuestionList category={category} />
    </div>
  );
};

export default CategoryQuestionListPage;

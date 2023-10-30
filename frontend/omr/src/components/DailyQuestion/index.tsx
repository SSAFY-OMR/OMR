import React from 'react';

import styles from './index.module.scss';
import QuestionView from '../QuestionView';

const DailyQuestion = () => {
  return (
    <div className={styles.DailyQuestion}>
      <div className={styles.title}>오늘의 문제가 도착했어요.</div>
      {/** dummy data */}
      <QuestionView
        question={{
          category: {
            id: 0,
            name: 'NETWORK',
            description: '네트워크',
          },
          content: 'OSI 7계층에 대해 설명해보세요. ',
          isScrapped: false,
          answer: undefined,
        }}
        type="listItem"
      />
    </div>
  );
};

export default DailyQuestion;

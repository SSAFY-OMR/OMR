import React, { useState } from 'react';

import styles from './index.module.scss';
import AnswerList from '../AnswerList';
import TabMenu from '../UI/TabMenu';

import { answerTabMenuList } from '@/constants/menu';

const AnswerListView = ({ questionId }: { questionId: string }) => {
  const [currentTab, setCurrentTab] = useState(0);

  return (
    <div className={styles.AnswerListView}>
      <TabMenu
        menuList={answerTabMenuList}
        currentTab={currentTab}
        setCurrentTab={setCurrentTab}
      />
      <AnswerList
        questionId={questionId}
        answerType={answerTabMenuList[currentTab].menuType}
      />
    </div>
  );
};

export default AnswerListView;

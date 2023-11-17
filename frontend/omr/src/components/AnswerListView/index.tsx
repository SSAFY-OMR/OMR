import React, { useState } from 'react';

import styles from './index.module.scss';
import AnswerList from '../AnswerList';
import ChatbotAnswer from '../ChatbotAnswer';
import TabMenu from '../UI/TabMenu';

import type { Question } from '@/types/question';

import { answerTabMenuList } from '@/constants/menu';

const AnswerListView = ({
  question,
  questionId,
}: {
  question: Question;
  questionId: string;
}) => {
  const [currentTab, setCurrentTab] = useState(0);

  return (
    <div className={styles.AnswerListView}>
      <TabMenu
        menuList={answerTabMenuList}
        currentTab={currentTab}
        setCurrentTab={setCurrentTab}
      />
      {answerTabMenuList[currentTab].menuType === 'chat' ? (
        <ChatbotAnswer question={question} />
      ) : (
        <AnswerList
          questionId={questionId}
          answerType={answerTabMenuList[currentTab].menuType}
        />
      )}
    </div>
  );
};

export default AnswerListView;

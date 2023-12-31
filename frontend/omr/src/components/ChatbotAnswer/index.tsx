import React, { useEffect, useState } from 'react';

import MDEditor from '@uiw/react-md-editor';

import ChatbotAnswerLoading from './ChatbotAnswerLoading';
import styles from './index.module.scss';

import type { Question } from '@/types/question';

import { getSampleAnswer } from '@/service/answer';

type AnswerListProps = {
  question: Question;
};

const ChatbotAnswer = ({ question }: AnswerListProps) => {
  const [answer, setAnswer] = useState('');

  useEffect(() => {
    (async () => {
      const res = await getSampleAnswer(
        question.category.name,
        question.content,
      );

      if (res?.status === 200) {
        if (res.data.message === 'success') {
          return setAnswer(res.data.data);
        }
        return setAnswer(res.data.message);
      }
      setAnswer('답을 불러오는 데 실패했어요. 😥');
    })();
  }, [question.category.name, question.content]);

  return (
    <div className={styles.AnswerList}>
      {answer ? (
        <div className={`${styles.UserAnswerView}`} data-color-mode="light">
          <div className={styles.header}>
            <div className={styles.profile}>
              <div className={styles.profileEmojiContainer}>
                <div className={styles.profileEmoji}>🤖</div>
              </div>
              <div className={styles.nickname}>Chatbot</div>
            </div>
          </div>
          <MDEditor.Markdown source={answer.replaceAll(/ \*\*|\*\* /g, '**')} />
        </div>
      ) : (
        <>
          <ChatbotAnswerLoading />
          <div className={styles.chatbotMessage}>
            챗봇이 답변 중이에요. 잠시만 기다려 주세요. 🤗
          </div>
        </>
      )}
    </div>
  );
};

export default ChatbotAnswer;

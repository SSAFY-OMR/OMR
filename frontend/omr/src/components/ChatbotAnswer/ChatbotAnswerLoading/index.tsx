import React from 'react';

import styles from './index.module.scss';

const ChatbotAnswerLoading = () => {
  return (
    <div className={`${styles.UserAnswerView}`}>
      <div className={styles.header}>
        <div className={styles.profile}>
          <div className={styles.profileEmojiContainer}>
            <div className={styles.profileEmoji}>🤖</div>
          </div>
          <div className={styles.nickname}>Chatbot</div>
        </div>
      </div>
      <div className={`${styles.blinking} ${styles.content}`}></div>
      <div className={`${styles.blinking} ${styles.content}`}></div>
      <div className={`${styles.blinking} ${styles.content}`}></div>
    </div>
  );
};

export default ChatbotAnswerLoading;

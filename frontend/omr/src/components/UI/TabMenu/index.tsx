import React from 'react';

import styles from './index.module.scss';

import type { AnswerTabItem } from '@/constants/menu';

type TabMenuProps = {
  currentTab: number;
  setCurrentTab: React.Dispatch<React.SetStateAction<number>>;
  menuList: { menuType: string; title: string }[];
};

const TabMenu = ({ currentTab, setCurrentTab, menuList }: TabMenuProps) => {
  return (
    <div className={styles.TabMenu}>
      {menuList.map((menuItem, i) => (
        <button
          key={i}
          className={`${styles.tabItem} ${
            currentTab === i ? styles.active : ``
          }`}
          onClick={() => setCurrentTab(i)}
        >
          {menuItem.title}
        </button>
      ))}
    </div>
  );
};

export default TabMenu;

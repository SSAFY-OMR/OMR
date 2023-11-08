import React from 'react';

import styles from './index.module.scss';

type TabMenuProps = {
  currentTab: number;
  setCurrentTab: React.Dispatch<React.SetStateAction<number>>;
  menuList: { menuType: string; title: string }[];
};

const TabMenu = ({ currentTab, setCurrentTab, menuList }: TabMenuProps) => {
  return (
    <div className={styles.TabMenu}>
      {menuList.map((menuItem, i) => (
        <div
          key={i}
          className={`clickable ${styles.tabItem} ${
            currentTab === i ? styles.active : ``
          }`}
          onClick={() => setCurrentTab(i)}
        >
          {menuItem.title}
        </div>
      ))}
    </div>
  );
};

export default TabMenu;

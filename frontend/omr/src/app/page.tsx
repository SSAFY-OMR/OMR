import styles from './index.module.scss';

import CalendarStreak from '@/components/CalendarStreak';
import CategoryCardList from '@/components/CategoryCardList';
import DailyQuestion from '@/components/DailyQuestion';

export default function HomePage() {
  return (
    <div className={styles.HomePage}>
      <CalendarStreak />
      <DailyQuestion />
      <CategoryCardList />
    </div>
  );
}

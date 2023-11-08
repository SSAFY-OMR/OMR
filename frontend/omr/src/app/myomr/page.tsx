'use client';

import { useEffect, useState } from 'react';

import styles from './index.module.scss';

import CategoryRadioGroup from '@/components/CategoryRadioGroup';
import QuestionListView from '@/components/QuestionListView';
import Paging from '@/components/UI/Pagination';
import TabMenu from '@/components/UI/TabMenu';
import { myOmrTabMenuList } from '@/constants/menu';
import useCategoryList from '@/hooks/useCategoryList';
import useFetcher from '@/hooks/useFetcher';
import { type Question } from '@/types/question';

type QuestionList = {
  questions: Question[];
  currentPage: number;
  totalPageCount: number;
};

const COUNT_PER_PAGE = 5;
const PAGE_RANGE = 5;

const MyOmr = () => {
  const { categoryList } = useCategoryList();

  const [currentTab, setCurrentTab] = useState(0);
  const [selectedCategory, setSelectedCategory] = useState<string>('ALL');
  const [page, setPage] = useState<number>(1);

  const noDataText = (): string => {
    let text = '';
    switch (myOmrTabMenuList[currentTab].menuType) {
      case 'scraped':
        text = '스크랩된 ';
        break;
      case 'solved':
        text = '해결한';
        break;
    }
    return text;
  };

  /**
   * 모드 및 카테고리 선택 변경 감지시 서버에서 유관 데이터 로드
   * 페이지네이션은 1페이지로 초기화한다.
   */
  useEffect(() => {
    setPage(1);
  }, [currentTab, selectedCategory]);

  const { data: questionList } = useFetcher<QuestionList>(
    `/history/questions/${myOmrTabMenuList[currentTab].menuType}`,
    true,
    selectedCategory === 'ALL'
      ? `?page=${page}&size=${COUNT_PER_PAGE}`
      : `?page=${page}&size=${COUNT_PER_PAGE}&category=${selectedCategory}`,
  );

  return (
    <div className={styles.wrapper}>
      <div className={styles.subheader}>
        <TabMenu
          currentTab={currentTab}
          setCurrentTab={setCurrentTab}
          menuList={myOmrTabMenuList}
        />
      </div>
      {/* category list */}
      <div className={styles.category}>
        {categoryList && (
          <CategoryRadioGroup
            categoryList={categoryList}
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
          />
        )}
      </div>
      {questionList && questionList.questions.length ? (
        <>
          <div className={styles.questionList}>
            {/* question list */}
            <QuestionListView questions={questionList.questions} />
          </div>
          {/* pagenation */}
          <div className={styles.paging}>
            <Paging
              page={page}
              setPage={setPage}
              countPerPage={COUNT_PER_PAGE}
              pageRange={PAGE_RANGE}
              totalCount={questionList.totalPageCount * COUNT_PER_PAGE}
            />
          </div>
        </>
      ) : (
        <div className={styles.nodata}>아직 {noDataText()} 문제가 없어요.</div>
      )}
    </div>
  );
};

export default MyOmr;

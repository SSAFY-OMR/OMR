'use client';
// this is the compont that will be rendered as a page
import { useEffect, useState } from 'react';

import { type AxiosResponse } from 'axios';

import CategoryRadioGroup from '@/components/CategoryRadioGroup';
import useCategoryList from '@/hooks/useCategoryList';
import useFetcher from '@/hooks/useFetcher';
import { type Question } from '@/types/question';

import styles from './index.module.scss';
import QuestionListView from '@/components/QuestionList/QuestionListView';

type SubHeader = {
  name: string;
  stringId: string;
};

type QuestionList = {
  questions: Question[];
  currentPage: number;
  totalPageCount: number;
};

const MyOmr = () => {
  const { categoryList } = useCategoryList();

  const [mode, setMode] = useState<string>('scraped'); // scraped || solved
  const [selectedCategory, setSelectedCategory] = useState<string>('ALL');
  const [page, setPage] = useState<number>(0);
  const subHeader: Array<SubHeader> = [
    { name: '보관한 문제', stringId: 'scraped' },
    { name: '내가 푼 문제', stringId: 'solved' },
  ];

  const handleSubheaderClick = (stringId: string) => {
    setMode(stringId);
  };
  const noDataText = (): string => {
    let text = '';
    switch (mode) {
      case 'scraped':
        text = '스크랩된 ';
        break;
      case 'solved':
        text = '해결한';
        break;
    }
    return text;
  };

  const size = 10;

  /**
   * 모드 및 카테고리 선택 변경 감지시 서버에서 유관 데이터 로드
   * 페이지네이션은 1페이지로 초기화한다.
   */
  useEffect(() => {
    console.log('SUBMODE ENGAGE!', mode, 'cat = ', selectedCategory);
    setPage(0);
    // load from server
    //
  }, [mode, selectedCategory]);

  const { data } = useFetcher<AxiosResponse<QuestionList>>(
    `/history/questions/${mode}`,
    true,
    `?page=${page}&size=${size}&category=${selectedCategory}`,
  );
  console.log('data  = ', data);

  return (
    <>
      <div className={styles.subheader}>
        {/* place header here */}
        {/* subheader */}
        {subHeader.map((item, idx) => {
          return (
            <div
              key={idx}
              className={`${styles.text} ${
                mode === item.stringId ? styles.active : ''
              }`}
              onClick={() => handleSubheaderClick(item.stringId)}
            >
              {item.name}
            </div>
          );
        })}
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
      {/* question list */}
      {data && data.data.questions.length ? (
        <QuestionListView questions={data.data.questions} />
      ) : (
        <div className={styles.nodata}>
          <h3>{noDataText()} 문제가 없습니다!</h3>
        </div>
      )}
      {/* pagenation */}
      
    </>
  );
};

export default MyOmr;

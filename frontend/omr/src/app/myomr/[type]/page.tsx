'use client';

import { useEffect, useState } from 'react';

import styles from './index.module.scss';

import CategoryRadioGroup from '@/components/CategoryRadioGroup';
import QuestionListView from '@/components/QuestionListView';
import Paging from '@/components/UI/Pagination';
import useCategoryCount from '@/hooks/useCategoryCount';
import useFetcher from '@/hooks/useFetcher';
import { type Question } from '@/types/question';

type QuestionList = {
  questions: Question[];
  currentPage: number;
  totalPageCount: number;
};

const COUNT_PER_PAGE = 5;
const PAGE_RANGE = 5;

const MyOmr = ({ params }: { params: { type: string } }) => {
  const type = params.type;

  const { categoryCount } = useCategoryCount();

  const [selectedCategory, setSelectedCategory] = useState<string>('ALL');
  const [page, setPage] = useState<number>(1);

  /**
   * 모드 및 카테고리 선택 변경 감지시 서버에서 유관 데이터 로드
   * 페이지네이션은 1페이지로 초기화한다.
   */
  useEffect(() => {
    setPage(1);
  }, [selectedCategory]);

  const { data: questionList } = useFetcher<QuestionList>(
    `/omr-api/history/questions/${type}`,
    true,
    selectedCategory === 'ALL'
      ? `?page=${page}&size=${COUNT_PER_PAGE}`
      : `?page=${page}&size=${COUNT_PER_PAGE}&category=${selectedCategory}`,
  );

  return (
    <div className={styles.wrapper}>
      <div className={styles.subheader}>
        {type === 'solved' ? '해결한' : '스크랩한'} 문제
      </div>
      {/* category list */}
      <div className={styles.category}>
        {categoryCount && (
          <CategoryRadioGroup
            categoryCount={categoryCount}
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
        <div className={styles.nodata}>
          아직 {type === 'solved' ? '해결한' : '스크랩된'} 문제가 없어요.
        </div>
      )}
    </div>
  );
};

export default MyOmr;

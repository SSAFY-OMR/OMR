package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionCategoryCountElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {

    @Query(value = """
                select q
                from InterviewQuestion q
                where q.interviewCategory =:category
            """)
    Page<InterviewQuestion> findQuestionByCategory(@Param("category") InterviewCategory category, Pageable pageable);

    @Query(value = """
                select q
                from InterviewQuestion q
            """)
    Page<InterviewQuestion> findQuestions(Pageable pageable);

    @Query(value = """
                select q
                from InterviewQuestion q
                order by rand(:seed)
                limit 1
            """)
    Optional<InterviewQuestion> findRandomQuestion(@Param("seed") Integer seed);


    @Query(value = """
                select 
                    new com.ssafy.omr.modules.question.dto.QuestionCategoryCountElement(q.interviewCategory, count(q.interviewCategory))
                from InterviewQuestion q
                group by q.interviewCategory
            """)
    List<QuestionCategoryCountElement> findCategoryCount();
}

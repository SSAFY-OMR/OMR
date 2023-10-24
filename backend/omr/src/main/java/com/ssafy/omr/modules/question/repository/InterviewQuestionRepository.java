package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    @Query(value = """
       select new com.ssafy.omr.modules.question.dto.QuestionElement(q.interviewCategory, q.content)
       from InterviewQuestion q
       where q.interviewCategory=:category
    """)
    Page<QuestionElement> findQuestionsByCategory(@Param("category") InterviewCategory category, Pageable pageable);
}

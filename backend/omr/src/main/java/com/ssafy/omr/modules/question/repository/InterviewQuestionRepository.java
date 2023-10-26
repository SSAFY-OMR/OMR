package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    @Query(value = """
       select new com.ssafy.omr.modules.question.dto.QuestionElement(q.interviewCategory, q.content)
       from InterviewQuestion q
       where q.interviewCategory=:category
    """)
    Page<QuestionElement> findQuestionsByCategory(@Param("category") InterviewCategory category, Pageable pageable);

    @Query(value = """
        select new com.ssafy.omr.modules.question.dto.QuestionElement(q.interviewCategory, q.content)
        from InterviewQuestion q
    """)
    Page<QuestionElement> findQuestions(PageRequest pageRequest);

    @Query(value = """
        select q
        from InterviewQuestion q
        order by rand(:seed)
        limit 1
    """)
    Optional<InterviewQuestion> findRandomQuestion(@Param("seed") Integer seed);
}

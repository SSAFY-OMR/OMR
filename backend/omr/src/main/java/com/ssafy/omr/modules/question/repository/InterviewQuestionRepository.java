package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.dto.QuestionCategoryCountElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long>, InterviewQuestionRepositoryCustom {

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

package com.ssafy.omr.modules.answer.repository;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = """
                select a
                from Answer a
                where a.interviewQuestion=:interviewQuestion and a.member=:member
                order by a.createdAt desc
                limit 1
            """)
    Optional<Answer> findByInterviewQuestionAndMemberOfRecent(InterviewQuestion interviewQuestion, Member member);

    @Query(value = """
                select a
                from Answer a inner join a.member
                where a.interviewQuestion.questionId=:questionId
            """)
    Page<Answer> getAnswerListByQuestion(Long questionId, Pageable pageRequest);
}

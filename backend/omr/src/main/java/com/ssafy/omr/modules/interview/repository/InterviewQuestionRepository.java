package com.ssafy.omr.modules.interview.repository;

import com.ssafy.omr.modules.interview.domain.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion,Long> {
}

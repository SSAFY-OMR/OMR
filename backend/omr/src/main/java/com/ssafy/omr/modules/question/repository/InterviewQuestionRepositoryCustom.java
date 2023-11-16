package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewQuestionRepositoryCustom {
    Page<InterviewQuestion> findQuestionsByCategory(Pageable pageable, String category);

    InterviewQuestion findNextQuestion(InterviewQuestion interviewQuestion, Boolean isAll);
}

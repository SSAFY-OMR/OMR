package com.ssafy.omr.modules.question.service;

import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final InterviewQuestionRepository interviewQuestionRepository;
}

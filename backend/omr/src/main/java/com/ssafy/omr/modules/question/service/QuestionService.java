package com.ssafy.omr.modules.question.service;

import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.dto.GetQuestionsRequest;
import com.ssafy.omr.modules.question.dto.GetQuestionsResponse;
import com.ssafy.omr.modules.question.dto.QuestionElement;
import com.ssafy.omr.modules.question.mapper.QuestionMapper;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final InterviewQuestionRepository interviewQuestionRepository;
    private final QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public GetQuestionsResponse getQuestionsByCategory(AuthInfo authInfo, GetQuestionsRequest getQuestionsRequest) {
        PageRequest pageRequest = getQuestionsRequest.toPageRequest();
        Page<QuestionElement> questionElements = interviewQuestionRepository.findQuestionsByCategory(InterviewCategory.ofName(getQuestionsRequest.getCategory()), pageRequest);
        return questionMapper.supplyGetQuestionsResponse(questionElements);
    }
}

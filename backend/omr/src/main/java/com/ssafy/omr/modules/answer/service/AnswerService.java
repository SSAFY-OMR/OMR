package com.ssafy.omr.modules.answer.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.dto.CreateAnswerRequest;
import com.ssafy.omr.modules.answer.dto.CreateAnswerResponse;
import com.ssafy.omr.modules.answer.mapper.AnswerMapper;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.token.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;
    /**
     * 특정 문제에 답변을 작성한다.
     * TODO : 스트릭 관련은 EventListener 를 활용해보는 것을 검토
     * @param loginUser 로그인된 유저 정보
     * @param createAnswerRequest 신규 답변 정보가 포함된 객체.
     * @return 신규 작성된 답변의 ID가 포함된 dto 응답객체
     */
    @Transactional
    public CreateAnswerResponse createAnswer(LoginUser loginUser, CreateAnswerRequest createAnswerRequest) {
//        Answer answer = Answer.builder()

    }
}

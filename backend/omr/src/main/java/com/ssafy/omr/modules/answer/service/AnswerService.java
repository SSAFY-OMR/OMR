package com.ssafy.omr.modules.answer.service;

import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.domain.AnswerLike;
import com.ssafy.omr.modules.answer.dto.*;
import com.ssafy.omr.modules.answer.exception.AnswerForbiddenException;
import com.ssafy.omr.modules.answer.exception.AnswerNotFoundException;
import com.ssafy.omr.modules.answer.mapper.AnswerMapper;
import com.ssafy.omr.modules.answer.repository.AnswerLikeRepository;
import com.ssafy.omr.modules.answer.repository.AnswerRepository;
import com.ssafy.omr.modules.auth.dto.AuthInfo;
import com.ssafy.omr.modules.global.event.CreatedAnswerEvent;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.member.domain.RoleType;
import com.ssafy.omr.modules.member.repository.MemberRepository;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.exception.InterviewQuestionNotFoundException;
import com.ssafy.omr.modules.question.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
  private final ApplicationEventPublisher applicationEventPublisher;
  private final AnswerRepository answerRepository;
  private final AnswerLikeRepository answerLikeRepository;
  private final MemberRepository memberRepository;
  private final InterviewQuestionRepository interViewQuestionRepository;

  /**
   * 특정 문제에 답변을 작성한다.
   * 문제를 푼 사람의 id를 기반으로 createdAnswer 이벤트를 발생시킨다.
   *
   * @param authInfo            로그인된 유저 정보
   * @param createAnswerRequest 신규 답변 정보가 포함된 객체.
   * @return 신규 작성된 답변의 ID가 포함된 dto 응답객체
   */
  @Transactional
  public CreateAnswerResponse createAnswer(AuthInfo authInfo, CreateAnswerRequest createAnswerRequest) {
    Member memberRef = memberRepository.getReferenceById(authInfo.id());
    InterviewQuestion interviewQuestionRef = interViewQuestionRepository
            .findById(createAnswerRequest.questionId())
            .orElseThrow(InterviewQuestionNotFoundException::new);

    Answer createdAnswer = answerRepository.save(
            AnswerMapper.supplyAnswerOf(memberRef, interviewQuestionRef, createAnswerRequest)
    );
    applicationEventPublisher.publishEvent(new CreatedAnswerEvent(authInfo.id()));
    return AnswerMapper.supplyCreateAnswerResponseFrom(createdAnswer);
  }

  /**
   * 답변 내용을 갱신한다.
   * 내부적으로 권환 확인을 수행
   *
   * @param authInfo            권한 객체
   * @param updateAnswerRequest 갱신 내용이 담긴 객체
   */
  @Transactional
  public void updateAnswer(AuthInfo authInfo, UpdateAnswerRequest updateAnswerRequest) {
    Answer answer = answerRepository
            .findById(updateAnswerRequest.answerId())
            .orElseThrow(AnswerNotFoundException::new);

    if (checkEditable(authInfo, answer.getMember().getId())) {
      throw new AnswerForbiddenException();
    }
    answer.updateContent(updateAnswerRequest.content());
  }

  /**
   * 답변을 삭제처리한다.
   * 내부적으로 권환 확인을 진행한다.
   *
   * @param authInfo            권한 객체
   * @param deleteAnswerRequest 삭제 답변의 정보가 담긴 객체
   */
  @Transactional
  public void deleteAnswer(AuthInfo authInfo, DeleteAnswerRequest deleteAnswerRequest) {
    Answer answer = answerRepository
            .findById(deleteAnswerRequest.answerId())
            .orElseThrow(AnswerNotFoundException::new);

    if (checkEditable(authInfo, answer.getMember().getId())) {
      throw new AnswerForbiddenException();
    }
    answerRepository.delete(answer);
  }

  /**
   * 답변에 대해 좋아요 처리 수행
   * 좋아요가 없으면 신규 좋아요 객체 생성
   * 좋아요가 있으면 좋아요 객체 삭제
   *
   * @param authInfo                권한 객체
   * @param toggleLikeAnswerRequest 답변 정보 객체
   * @return 좋아요 여부가 담긴 응답객체
   */
  @Transactional
  public ToggleLikeAnswerResponse toggleAnswerLike(AuthInfo authInfo, ToggleLikeAnswerRequest toggleLikeAnswerRequest) {
    Member member = memberRepository.getReferenceById(authInfo.id());
    Answer answer = answerRepository.findById(toggleLikeAnswerRequest.answerId())
            .orElseThrow(AnswerNotFoundException::new);

    AnswerLike answerLike = answerLikeRepository.findByMemberAndAnswer(member, answer);
    boolean isToggled = true;
    if (answerLike != null) {
      isToggled = false;
      removeAnswerLike(answerLike);
      answer.decreaseLikeCount();
      return AnswerMapper.supplyToggleLikeAnswerResponseOf(isToggled);
    }
    createAnswerLike(member, answer);
    answer.increaseLikeCount();
    return AnswerMapper.supplyToggleLikeAnswerResponseOf(isToggled);
  }

  private boolean checkEditable(AuthInfo authInfo, Long answerOwnerId) {
    boolean isOwner = answerOwnerId.equals(authInfo.id());
    boolean isAdmin = authInfo.role().equals(RoleType.ADMIN.getName());
    return isOwner || isAdmin;
  }

  private void removeAnswerLike(AnswerLike answerLike) {
    answerLikeRepository.delete(answerLike);
  }

  private void createAnswerLike(Member member, Answer answer) {
    AnswerLike answerLike = AnswerLike.builder()
            .member(member)
            .answer(answer)
            .build();
    answerLikeRepository.save(answerLike);
  }

  public QuestionAnswerResponse getAnswerList(Long questionId, Pageable pageRequest) {
    Page<AnswerResponse> answerList = answerRepository
            .findAnswerListByQuestion(interViewQuestionRepository.getReferenceById(questionId), pageRequest)
            .map(AnswerMapper::supplyAnswerResponseOf);
    return AnswerMapper.supplyQuestionAnswerResponseOf(questionId, answerList);
  }

  @Transactional(readOnly = true)
  public AnswerListResponse getOthersAnswerList(Long questionId, AuthInfo authInfo) {
    InterviewQuestion interviewQuestion = interViewQuestionRepository.getReferenceById(questionId);
    Member member = memberRepository.getReferenceById(authInfo.id());

    List<Answer> answers = answerRepository.findOthersAnswerListByQuestionAndMember(interviewQuestion, member);
    List<AnswerResponse> answerResponses = answers.stream().map(AnswerMapper::supplyAnswerResponseOf).toList();

    return AnswerMapper.supplyQuestionAnswerResponseFrom(answerResponses);
  }

  @Transactional(readOnly = true)
  public AnswerListResponse getMyAnswer(Long questionId, AuthInfo authInfo) {
    InterviewQuestion interviewQuestion = interViewQuestionRepository.getReferenceById(questionId);
    Member member = memberRepository.getReferenceById(authInfo.id());
    List<Answer> answers = answerRepository.findMyAnswerListByInterviewQuestionAndMember(interviewQuestion, member);
    List<AnswerResponse> answerResponses = answers.stream().map(AnswerMapper::supplyAnswerResponseOf).toList();
    return AnswerMapper.supplyQuestionAnswerResponseFrom(answerResponses);
  }
}

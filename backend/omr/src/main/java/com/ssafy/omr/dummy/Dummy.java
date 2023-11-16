package com.ssafy.omr.dummy;

import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
@Profile("dev")
public class Dummy implements CommandLineRunner {

    private final EntityManager em;
    private final MemberDummy memberDummy;
    private final QuestionDummy questionDummy;

    private void flushAndClear(){
        em.flush();
        em.clear();
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("dummy insertion start");

        // dummy 객체 생성 및 저장
        List<Member> members = memberDummy.createMemberDummies();
        List<InterviewQuestion> interviewQuestions = questionDummy.createInterviewQuestionDummies();
        flushAndClear();

        log.info("dummy insertion finished");
    }

}

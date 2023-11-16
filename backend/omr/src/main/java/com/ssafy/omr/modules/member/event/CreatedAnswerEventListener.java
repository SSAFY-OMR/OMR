package com.ssafy.omr.modules.member.event;

import com.ssafy.omr.modules.global.event.CreatedAnswerEvent;
import com.ssafy.omr.modules.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class CreatedAnswerEventListener {

    private final MemberService memberService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void creatStreak(CreatedAnswerEvent createdAnswerEvent){
        Long memberId = createdAnswerEvent.memberId();
        memberService.updateMemberStreakStatus(memberId);
    }
}

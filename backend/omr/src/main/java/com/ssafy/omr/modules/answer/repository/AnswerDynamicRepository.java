package com.ssafy.omr.modules.answer.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.answer.domain.Answer;
import com.ssafy.omr.modules.answer.domain.AnswerLike;
import com.ssafy.omr.modules.answer.domain.QAnswerLike;
import com.ssafy.omr.modules.member.domain.Member;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.omr.modules.answer.domain.QAnswer.answer;
import static com.ssafy.omr.modules.answer.domain.QAnswerLike.*;
import static com.ssafy.omr.modules.member.domain.QMember.member;

@RequiredArgsConstructor
@Repository
public class AnswerDynamicRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //    @Query(value = """
//                select a
//                from Answer a inner join a.member m
//                where a.interviewQuestion = :question and a.member <> :member
//
//            """)
    public Page<Answer> findOthersAnswerListByQuestionAndMember(InterviewQuestion interviewQuestion, Member memberRef, Pageable pageable) {
        List<Answer> content = jpaQueryFactory
                .select(answer)
                .from(answer)
                .leftJoin(answer.member, member)
                .where(
                        answer.interviewQuestion.eq(interviewQuestion),
                        answer.member.ne(memberRef)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(answer.likeCount.desc())
                .fetch();
        JPAQuery<Long> totalPageCount =
                jpaQueryFactory
                        .select(answer.count())
                        .from(answer)
                        .where(
                                answer.interviewQuestion.eq(interviewQuestion),
                                answer.member.ne(memberRef)
                        );
        return PageableExecutionUtils.getPage(content,pageable, totalPageCount::fetchOne);
    }

    public List<AnswerLike> findAnswerLikesByAnswerAndMember(List<Answer> answers, Member member) {
        return jpaQueryFactory
                .selectFrom(answerLike)
                .innerJoin(answerLike.member)
                .fetchJoin()
                .where(answerLike.answer.in(answers), answerLike.member.eq(member))
                .fetch();
    }

    public Page<Answer> findMyAnswerListByInterviewQuestionAndMember(InterviewQuestion interviewQuestion, Member memberRef, Pageable pageable) {
        List<Answer> content = jpaQueryFactory
                .select(answer)
                .from(answer)
                .leftJoin(answer.member, member)
                .where(
                        answer.interviewQuestion.eq(interviewQuestion),
                        answer.member.eq(memberRef)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(answer.likeCount.desc())
                .fetch();
        JPAQuery<Long> totalPageCount =
                jpaQueryFactory
                        .select(answer.count())
                        .from(answer)
                        .where(
                                answer.interviewQuestion.eq(interviewQuestion),
                                answer.member.ne(memberRef)
                        );
        return PageableExecutionUtils.getPage(content,pageable, totalPageCount::fetchOne);
    }

}

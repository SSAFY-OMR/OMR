package com.ssafy.omr.modules.question.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.omr.modules.question.domain.QInterviewQuestion.interviewQuestion;

@Repository
@RequiredArgsConstructor
public class InterviewQuestionRepositoryImpl implements InterviewQuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<InterviewQuestion> findQuestionsByCategory(Pageable pageable, String category) {
        List<InterviewQuestion> interviewQuestions = jpaQueryFactory.select(interviewQuestion)
                .from(interviewQuestion)
                .where(categoryEq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(interviewQuestion.id.desc())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(interviewQuestion.count())
                .from(interviewQuestion)
                .where(categoryEq(category));

        return PageableExecutionUtils.getPage(interviewQuestions, pageable, count::fetchOne);
    }

    private BooleanExpression categoryEq(String category) {
        if (category == null)
            return null;
        return interviewQuestion.interviewCategory.eq(InterviewCategory.ofName(category));
    }
}

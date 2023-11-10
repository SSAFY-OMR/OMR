package com.ssafy.omr.modules.question.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.domain.InterviewQuestion;
import com.ssafy.omr.modules.question.domain.QInterviewQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterviewQuestionRepositoryImpl implements InterviewQuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QInterviewQuestion qInterviewQuestion = QInterviewQuestion.interviewQuestion;

    public Page<InterviewQuestion> findQuestionsByCategory(Pageable pageable, String category) {
        List<InterviewQuestion> interviewQuestions = jpaQueryFactory.select(qInterviewQuestion)
                .from(qInterviewQuestion)
                .where(categoryEq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qInterviewQuestion.id.desc())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(qInterviewQuestion.count())
                .from(qInterviewQuestion)
                .where(categoryEq(category));

        return PageableExecutionUtils.getPage(interviewQuestions, pageable, count::fetchOne);
    }

    @Override
    public InterviewQuestion findNextQuestion(InterviewQuestion interviewQuestion, Boolean isAll) {
        if (isAll) {
            return jpaQueryFactory.select(qInterviewQuestion)
                    .from(qInterviewQuestion)
                    .where(qInterviewQuestion.id.lt(interviewQuestion.getId()))
                    .orderBy(qInterviewQuestion.id.desc())
                    .fetchFirst();
        }
        return jpaQueryFactory.select(qInterviewQuestion)
                .from(qInterviewQuestion)
                .where(interviewCategoryEq(interviewQuestion.getInterviewCategory()),
                        qInterviewQuestion.id.lt(interviewQuestion.getId()))
                .orderBy(qInterviewQuestion.id.desc())
                .fetchFirst();
    }

    private BooleanExpression categoryEq(String category) {
        if (category == null)
            return null;
        return qInterviewQuestion.interviewCategory.eq(InterviewCategory.ofName(category));
    }

    private BooleanExpression interviewCategoryEq(InterviewCategory interviewCategory) {
        return qInterviewQuestion.interviewCategory.eq(interviewCategory);
    }
}

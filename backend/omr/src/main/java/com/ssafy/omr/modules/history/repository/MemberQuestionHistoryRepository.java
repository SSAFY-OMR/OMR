package com.ssafy.omr.modules.history.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.omr.modules.meta.domain.InterviewCategory;
import com.ssafy.omr.modules.question.dto.QuestionElement;

import static com.ssafy.omr.modules.scrap.domain.QInterviewQuestionScrap.interviewQuestionScrap;
import static com.ssafy.omr.modules.question.domain.QInterviewQuestion.interviewQuestion;
import static com.ssafy.omr.modules.answer.domain.QAnswer.answer;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberQuestionHistoryRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public Page<QuestionElement> findByScrapedQuestion(Long memberId, String category, Pageable pageable) {
		List<QuestionElement> questions = getQuestionPagingBaseQuery(category, pageable)
			.innerJoin(interviewQuestionScrap)
			.on(interviewQuestionScrap.interviewQuestion.id.eq(interviewQuestion.id))
			.where(interviewQuestionScrap.member.id.eq(memberId))
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory
			.select(Wildcard.count)
			.from(interviewQuestionScrap)
			.where(interviewQuestionScrap.member.id.eq(memberId));

		return PageableExecutionUtils.getPage(questions, pageable, countQuery::fetchFirst);
	}

	public Page<QuestionElement> findBySolvedQuestion(Long memberId, String category, Pageable pageable) {
		List<QuestionElement> questions = getQuestionPagingBaseQuery(category, pageable)
			.innerJoin(answer)
			.on(answer.interviewQuestion.id.eq(interviewQuestion.id))
			.where(answer.member.id.eq(memberId))
			.fetch();

		JPAQuery<Long> countQuery = jpaQueryFactory.select(Wildcard.count)
			.from(answer)
			.where(answer.member.id.eq(memberId));

		return PageableExecutionUtils.getPage(questions, pageable, countQuery::fetchFirst);
	}

	private JPAQuery<QuestionElement> getQuestionPagingBaseQuery(String category, Pageable pageable) {
		return jpaQueryFactory
			.select(
				getQuestionElement(
					interviewQuestion.id,
					interviewQuestion.interviewCategory,
					interviewQuestion.content
				)
			)
			.from(interviewQuestion)
			.where(interviewCategoryEq(interviewQuestion.interviewCategory, category))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(interviewQuestion.id.desc());
	}

	private ConstructorExpression<QuestionElement> getQuestionElement(
		NumberPath<Long> questionId,
		EnumPath<InterviewCategory> interviewCategory, StringPath content) {
		return Projections.constructor(
			QuestionElement.class,
			questionId,
			interviewCategory,
			content);
	}

	private BooleanExpression interviewCategoryEq(EnumPath<com.ssafy.omr.modules.meta.domain.InterviewCategory> categoryEnumPath, String category) {
		try {
			InterviewCategory interviewCategory = InterviewCategory.valueOf(category);
			return categoryEnumPath.eq(interviewCategory);
		} catch(IllegalArgumentException e) {
			return null;
		}
	}
}

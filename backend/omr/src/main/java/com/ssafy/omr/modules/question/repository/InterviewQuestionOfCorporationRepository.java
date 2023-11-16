package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.question.domain.InterviewQuestionOfCorporation;
import com.ssafy.omr.modules.question.dto.QuestionCorporationCountElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewQuestionOfCorporationRepository extends JpaRepository<InterviewQuestionOfCorporation, Long> {
    Page<InterviewQuestionOfCorporation> findAllByCorporationType(CorporationType corporationType, Pageable pageable);

    @Query(value = """
                select new com.ssafy.omr.modules.question.dto.QuestionCorporationCountElement(q.corporationType, count(q.corporationType))
                from InterviewQuestionOfCorporation q
                group by q.corporationType
            """)
    List<QuestionCorporationCountElement> findCorporationTypeCount();
}

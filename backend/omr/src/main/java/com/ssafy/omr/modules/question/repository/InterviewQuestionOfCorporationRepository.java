package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.meta.domain.CorporationType;
import com.ssafy.omr.modules.question.domain.InterviewQuestionOfCorporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionOfCorporationRepository extends JpaRepository<InterviewQuestionOfCorporation, Long> {
    Page<InterviewQuestionOfCorporation> findAllByCorporationType(CorporationType corporationType, Pageable pageable);
}

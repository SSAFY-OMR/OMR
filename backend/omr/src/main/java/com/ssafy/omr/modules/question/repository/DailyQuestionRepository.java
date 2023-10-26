package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.question.domain.DailyQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuestionRepository extends CrudRepository<DailyQuestion, Integer> {
}

package com.ssafy.omr.modules.question.repository;

import com.ssafy.omr.modules.question.domain.DailyQuestionRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuestionRedisRepository extends CrudRepository<DailyQuestionRedis, Integer> {
}

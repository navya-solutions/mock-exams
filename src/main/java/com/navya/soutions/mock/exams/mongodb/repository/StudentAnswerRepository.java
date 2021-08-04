package com.navya.soutions.mock.exams.mongodb.repository;

import com.navya.soutions.mock.exams.mongodb.model.StudentAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends MongoRepository<StudentAnswer, String> {
}

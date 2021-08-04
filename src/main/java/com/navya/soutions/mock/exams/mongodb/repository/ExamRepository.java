package com.navya.soutions.mock.exams.mongodb.repository;

import com.navya.soutions.mock.exams.mongodb.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends MongoRepository<Exam, String> {
}

package com.navya.soutions.mock.exams.mongodb.repository;

import com.navya.soutions.mock.exams.mongodb.model.StudentAssessment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssessmentRepository extends MongoRepository<StudentAssessment, String> {
}

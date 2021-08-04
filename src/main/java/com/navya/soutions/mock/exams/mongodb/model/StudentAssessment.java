package com.navya.soutions.mock.exams.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "student_assessment")
@Getter
@Setter
@ToString
@Builder
public class StudentAssessment {
    @Id
    private String id;
    private int revision;
    private Integer correctAnswerCount,wrongAnswerCount,totalMarks;
    private String comment;
    @DBRef(lazy = true)
    private Exam exam;
    @DBRef(lazy = true)
    private Student student;
}

package com.navya.soutions.mock.exams.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "EXAM")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private long date;
    private String description;
    private String otherDetails;
    private int markPerQuestion;//if set, override the question mark field

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_type_id")
    @JsonIgnore
    private SubjectType subjectType;


    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ExamQuestion> examQuestions = new HashSet<>();

    public void addExamQuestion(ExamQuestion examQuestion) {
        this.examQuestions.add(examQuestion);
        examQuestion.setExam(this);
    }

    public void removeExamQuestion(ExamQuestion examQuestion) {
        this.examQuestions.remove(examQuestion);
        examQuestion.setExam(null);
    }


}

package com.navya.soutions.mock.exams.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.navya.soutions.mock.exams.common.QuestionTypeEnum;
import com.navya.soutions.mock.exams.common.SubjectTypeEnum;
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
@Table(name = "QUESTION_TYPE")
public class QuestionType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum questionCode;
    @Enumerated(EnumType.STRING)
    private SubjectTypeEnum subjectCode;
    private String description;
    @OneToMany(mappedBy = "questionType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();

    public void addQuestion(Question question) {
        this.questions.add(question);
        question.setQuestionType(this);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setQuestionType(null);
    }
}

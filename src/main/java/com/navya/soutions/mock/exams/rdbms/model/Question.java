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
@Table(name = "QUESTION")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String questionText;
    private String hint;
    private String imageUrl;
    private int mark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id")
    @JsonIgnore
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ExamQuestion> examQuestions = new HashSet<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Answer> answers = new HashSet<>();

    public void addExamQuestion(ExamQuestion examQuestion) {
        this.examQuestions.add(examQuestion);
        examQuestion.setQuestion(this);
    }

    public void removeExamQuestion(ExamQuestion examQuestion) {
        this.examQuestions.remove(examQuestion);
        examQuestion.setQuestion(null);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setQuestion(null);
    }
}

package com.navya.soutions.mock.exams.rdbms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "SUBJECT_TYPE")
public class SubjectType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Enumerated(EnumType.STRING)
    private SubjectTypeEnum subjectCode;
    private String description;
    @OneToMany(mappedBy = "subjectType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Exam> exams = new HashSet<>();

    public void addExam(Exam exam) {
        this.exams.add(exam);
        exam.setSubjectType(this);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
        exam.setSubjectType(null);
    }
}

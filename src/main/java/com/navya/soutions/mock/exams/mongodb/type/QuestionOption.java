package com.navya.soutions.mock.exams.mongodb.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionOption implements Serializable {
    private String id;
    private String description;

}

package com.navya.soutions.mock.exams.mongodb.type;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerOption implements Serializable {
    private String id;
    private String description;

}

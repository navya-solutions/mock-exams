package com.navya.soutions.mock.exams.mongodb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "student")
@Getter
@Setter
@ToString
@Builder
public class Student {
    @Id
    private String id;
    private int revision;
    private String firstName,lastName, gender,address,email,phoneNumber,details;

}

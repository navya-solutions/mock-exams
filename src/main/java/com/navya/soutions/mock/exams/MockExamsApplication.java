package com.navya.soutions.mock.exams;

import com.navya.soutions.mock.exams.common.QuestionTypeEnum;
import com.navya.soutions.mock.exams.common.SubjectTypeEnum;
import com.navya.soutions.mock.exams.mongodb.model.*;
import com.navya.soutions.mock.exams.mongodb.repository.*;
import com.navya.soutions.mock.exams.mongodb.type.AnswerOption;
import com.navya.soutions.mock.exams.mongodb.type.QuestionOption;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class MockExamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockExamsApplication.class, args);
    }

    @Bean
    ApplicationRunner ApplicationRunner(QuestionRepository questionRepository,
                                        StudentRepository studentRepository,
                                        ExamRepository examRepository,
                                        StudentAnswerRepository studentAnswerRepository,
                                        StudentAssessmentRepository studentAssessmentRepository) {
        return args -> {
            final AnswerOption e1 = new AnswerOption();
            e1.setDescription("Desc");
            e1.setId("A");
            final QuestionOption e11 = new QuestionOption();
            e11.setId("A");
            e11.setDescription("Desc");
            final QuestionOption e12 = new QuestionOption();
            e12.setId("B");
            e12.setDescription("Desc");
            final Question question = getQuestion(questionRepository, e1, e11, e12);
            final Student student = getStudent(studentRepository);
            final Exam exam = getExam(examRepository, question);

            StudentAnswer studentAnswer = getStudentAnswer(studentAnswerRepository, e1, question, student, exam);

            StudentAssessment studentAssessment = StudentAssessment
                    .builder()
                    .comment("comment")
                    .student(student)
                    .exam(exam)
                    .correctAnswerCount(1)
                    .wrongAnswerCount(0)
                    .totalMarks(1)
                    .build();
            studentAssessmentRepository.save(studentAssessment);
        };
    }

    private StudentAnswer getStudentAnswer(StudentAnswerRepository studentAnswerRepository, AnswerOption e1, Question question, Student student, Exam exam) {
        StudentAnswer studentAnswer = StudentAnswer
                .builder()
                .revision(1)
                .answerDate(System.currentTimeMillis() / 1000)
                .answerOptions(Set.of(e1))
                .exam(exam)
                .student(student)
                .answerText("answerText not required for choice question")
                .question(question)
                .build();
        return studentAnswerRepository.save(studentAnswer);
    }

    private Exam getExam(ExamRepository examRepository, Question question) {
        Exam exam = Exam
                .builder()
                .revision(1)
                .createdAt(System.currentTimeMillis() / 1000)
                .description("Exam desc")
                .markPerQuestion(1)
                .otherDetails("Other details")
                .questions(Set.of(question))
                .subjectCode(SubjectTypeEnum.COMPUTER.toString())
                .build();
        final Exam savedExam = examRepository.save(exam);
        return savedExam;
    }

    private Student getStudent(StudentRepository studentRepository) {
        Student student = Student
                .builder()
                .revision(1)
                .address("Address")
                .details("Other details")
                .email("email")
                .firstName("firstName")
                .gender("M")
                .phoneNumber("PhoneNumber")
                .build();
        final Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    private Question getQuestion(QuestionRepository questionRepository, AnswerOption e1, QuestionOption e11, QuestionOption e12) {
        Question question = Question
                .builder()
                .revision(1)
                .answerOptions(Set.of(e1))
                .hint("question hint")
                .questionCode(QuestionTypeEnum.MULTIPLE_CHOICE_WITH_FREE_TEXT.toString())
                .questionOptions(Set.of(e11, e12))
                .imageUrl("http:....")
                .subjectCode(SubjectTypeEnum.COMPUTER.toString())
                .mark(1)

                .build();
        final Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

}

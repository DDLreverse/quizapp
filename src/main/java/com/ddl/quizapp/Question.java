package com.ddl.quizapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Data; // No need to write get and set methods for every variable

@Data
@Entity
public class Question {
    @Id
//    @GneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String difficultyLevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTitle;
    private String rightAnswer;
}

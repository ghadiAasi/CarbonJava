package com.example.carbonjava;

public class Question {
    private String question;
    private String rightAnswer;

    private Question(String question, String[] answsers, String rightAnswer){
        this.question = question;
        this.rightAnswer = rightAnswer;
    }
}

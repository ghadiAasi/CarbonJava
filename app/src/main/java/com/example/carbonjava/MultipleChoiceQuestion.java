package com.example.carbonjava;

public class MultipleChoiceQuestion {
    private String question;
    private String[] answers;
    private int rightAnswer;
    public final int ANS =4;

    private MultipleChoiceQuestion(String question, String[] answsers, int rightAnswer){
        this.question = question;
        this.rightAnswer = rightAnswer;
        for(int i=0;i<4;i++){
            this.answers[i]=answsers[i];
        }
    }
}

package com.example.myapplicationtest;

public class Question {
    private String questionBody;
    private int resOption1;
    private int resOption2;
    private int correctAns;

    public Question(String questionBody , int resOption1, int resOption2, int correctAns){
        this.correctAns = correctAns;
        this.resOption1 = resOption1;
        this.resOption2 = resOption2;
        this.questionBody = questionBody;
    }

    public int getCorrectAns() {
        return correctAns;
    }
    public int getResOption1() {
        return resOption1;
    }
    public int getResOption2() {
        return resOption2;
    }
    public String getQuestionBody() {
        return questionBody;
    }

    public void setResOption2(int resOption2) {
        this.resOption2 = resOption2;
    }

    public void setResOption1(int resOption1) {
        this.resOption1 = resOption1;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }
}

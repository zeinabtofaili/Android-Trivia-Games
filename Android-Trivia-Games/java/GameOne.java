package com.example.contentsgame;

public class GameOne {
    private int mQuestion;
    private int mCorrectAnswer;

    public GameOne(int question,int correctAnswer) {
        mQuestion = question;
        mCorrectAnswer = correctAnswer;

    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }



    public int getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }
    public boolean Score(int mUserAnswer){
        return mUserAnswer==mCorrectAnswer;
    }

}

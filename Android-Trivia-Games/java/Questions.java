package com.example.contentsgame;

public class Questions {


    private GameOne[] Questions=new GameOne[]{
            new GameOne(R.string.questionOne,R.id.Asia),
            new GameOne(R.string.questionTwo,R.id.Alasca),
            new GameOne(R.string.questionThree,R.id.Africa),
            new GameOne(R.string.questionFour,R.id.Europe),
            new GameOne(R.string.questionFive,R.id.Austrilia),
            new GameOne(R.string.questionSix,R.id.SouthUS),
            new GameOne(R.string.questionSeven,R.id.NorthUS)};
    public Questions() {

    }
    public GameOne[] getQuestions() {
        return Questions;
    }

    public void setQuestions(GameOne[] questions) {
        Questions = questions;
    }



}

package com.example.contentsgame;

public class Options {

    private String[] animalOptions;
    private int answerIndex;
    private int image;

    public Options(String[] animalOptions, int answerIndex, int image) {
        this.animalOptions = animalOptions;
        this.answerIndex = answerIndex;
        this.image = image;
    }

    public String[] getAnimalOptions(){
        return animalOptions;
    }

    public int getAnswerIndex(){
        return answerIndex;
    }

    public int getImage(){
        return image;
    }

    public void setAnimalOptions(String[] array){
        animalOptions = array;
    }

    public void setAnswerIndex(int index){
        answerIndex = index;
    }

    public void setImage(int image){
        this.image = image;
    }
}

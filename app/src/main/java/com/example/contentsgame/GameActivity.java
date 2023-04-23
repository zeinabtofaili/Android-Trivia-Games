package com.example.contentsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;
    private Button fourthBtn;
    private Button[] buttonsList;
    private Options[] optionsArray;
    private ImageView currentImage;
    private TextView questionNum;
    private TextView score;
    private int currentScore =0;
    private int questionIndex = 0;
    int chosenOption;
    Timer timer;
    int timeLeft = 10;
    TextView timeText;
    TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null) {
            currentScore = savedInstanceState.getInt("current_score", 0);
            questionIndex = savedInstanceState.getInt("current_question_index", 0);
            timeLeft = savedInstanceState.getInt("timeLeftToAnswer", 0);
        }

        optionsArray = new Options[]{
                new Options(this.getResources().getStringArray(R.array.dog_list), 1, R.drawable.dog),
                new Options(this.getResources().getStringArray(R.array.beaver_list), 1, R.drawable.beaver),
                new Options(this.getResources().getStringArray(R.array.buffalo_list), 0, R.drawable.buffalo),
                new Options(this.getResources().getStringArray(R.array.wolf_list), 3, R.drawable.wolf),
                new Options(this.getResources().getStringArray(R.array.eagle_list), 2, R.drawable.eagle),
                new Options(this.getResources().getStringArray(R.array.wallaby_list), 3, R.drawable.wallaby),
                new Options(this.getResources().getStringArray(R.array.turtle_list), 2, R.drawable.turtle),
                new Options(this.getResources().getStringArray(R.array.lion_list), 0, R.drawable.lion),
                new Options(this.getResources().getStringArray(R.array.zebra_list), 1, R.drawable.zebra),
                new Options(this.getResources().getStringArray(R.array.crocodile_list), 3, R.drawable.crocodile),
        };
        firstBtn = findViewById(R.id.firstOption);
        secondBtn = findViewById(R.id.secondOption);
        thirdBtn = findViewById(R.id.thirdOption);
        fourthBtn = findViewById(R.id.fourthOption);
        currentImage = findViewById(R.id.image);
        questionNum = findViewById(R.id.questionNum);
        score = findViewById(R.id.score);
        timeText = findViewById(R.id.quesTimer);
        buttonsList = new Button[]{firstBtn, secondBtn, thirdBtn, fourthBtn};

        startGame();
    }

    private void changeButtonTextAndImage(){
        for(int i=0; i<buttonsList.length; i++){
            buttonsList[i].setText(optionsArray[questionIndex].getAnimalOptions()[i]);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentImage.setImageResource(optionsArray[questionIndex].getImage());
                String stringQuestionNum = getText(R.string.questionNum) + " "+(questionIndex+1) +" out of 10";
                questionNum.setText(stringQuestionNum);
            }
        });
    }

    public void startGame(){
        String scoreString = getText(R.string.score) + " " + currentScore;
        score.setText(scoreString);
        changeButtonTextAndImage();
        runTimer();
        for(int i=0; i<buttonsList.length; i++){
            int finalI = i;
            buttonsList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chosenOption = finalI;
                    if(checkAnswer()){
                        Toast.makeText(GameActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                        calculateScore(true);
                    }else{
                        Toast.makeText(GameActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                        calculateScore(false);
                    }

                    goToNextQuestion();
                }
            });
        }
    }

    public boolean checkAnswer(){
        if(chosenOption == optionsArray[questionIndex].getAnswerIndex())
            return true;
        return false;
    }

    public void goToNextQuestion(){

        if(questionIndex+1>=optionsArray.length){
            isOver();
        }else{
            timer.cancel();
            timer.purge();
            timeLeft = 10;
            runTimer();
            questionIndex++;
            changeButtonTextAndImage();
        }
    }

    public void calculateScore(boolean answerResult){
        if(answerResult){
            currentScore+=2;
        }else{
            if(currentScore-1>=0){
                currentScore --;
            }else{
                currentScore =0;
            }
        }
        String scoreString = getText(R.string.score) + " " + currentScore;
        score.setText(scoreString);
    }

    public void isOver(){
        timer.cancel();
        timer.purge();
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        String scoreText = score.getText().toString();
        intent.putExtra("org.example.com.gameActivity.score",String.valueOf(currentScore));
        startActivity(intent);
        finish(); //so that the user cannot come back here and change the score
    }

    private void runTimer(){
        timerTask = new TimerTask(){
            public void run(){
                if(timeLeft>0){
                    timeLeft--;
                    changeTime();
                }else{
                    goToNextQuestion();
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void changeTime(){
        String timeString = getText(R.string.timer) + " " + timeLeft;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timeText.setText(timeString);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current_score", currentScore);
        outState.putInt("current_question_index", questionIndex);
        outState.putInt("timeLeftToAnswer", timeLeft);
    }
}


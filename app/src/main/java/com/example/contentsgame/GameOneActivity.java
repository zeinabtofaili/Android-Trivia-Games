package com.example.contentsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameOneActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private ImageButton mSouthUS;
    private ImageButton mNorthUS;
    private ImageButton mAsia;
    private ImageButton mAfrica;
    private ImageButton mEurope;
    private ImageButton mAlasca;
    private ImageButton mAustrilia;
    private TextView mScore;
    private TextView mQuestion;
    private TextView mQuestionNb;
    private TextView mGame;
    private Button mExit;
    Integer score=0;
    String Score_sent="0";
    private GameOne[] Questions=new Questions().getQuestions();
    Timer timer;
    int timeLeft = 10;
    TextView timeText;
    TimerTask timerTask;


    private int mCurrentIndex = 0;
    private int mIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt("index", 0);
            score = savedInstanceState.getInt("score", 0);
            timeLeft = savedInstanceState.getInt("timeLeftToAnswer", 0);
        }
        mSouthUS=findViewById(R.id.SouthUS);
        mNorthUS=findViewById(R.id.NorthUS);
        mAsia=findViewById(R.id.Asia);
        mAfrica=findViewById(R.id.Africa);
        mEurope=findViewById(R.id.Europe);
        mAlasca=findViewById(R.id.Alasca);
        mAustrilia=findViewById(R.id.Austrilia);
        mScore=findViewById(R.id.score_nb);
        mQuestion=findViewById(R.id.question);
        mQuestionNb=findViewById(R.id.question_nb);
        mGame=findViewById(R.id.Game_Over);
        mExit=findViewById(R.id.exit);

        timeText = findViewById(R.id.quesTimer);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOneActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.Africa);

            }
        });
        mNorthUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.NorthUS);



            }
        });

        mSouthUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.SouthUS);

            }
        });
        mAustrilia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.Austrilia);

            }
        });
        mAlasca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.Alasca);

            }
        });

        mEurope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.Europe);

            }
        });
        mAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(R.id.Asia);

            }
        });
        runTimer();
        updateQuestion();

    }
    private void runTimer(){
        timerTask = new TimerTask(){
            public void run(){
                if(timeLeft>0){
                    timeLeft--;
                    changeTime();
                }else{
                    if(mCurrentIndex <6){
                        mCurrentIndex = (mCurrentIndex + 1);
                        updateQuestion();
                    }
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

    private void checkAnswer(int theUserAnswer) {
        //isAnswerTrue() --> returns the True answer as a boolean (True or False)
        // Check Question.java

        boolean answerIsTrue = Questions[mCurrentIndex].Score(theUserAnswer);
        if(mCurrentIndex<7){
            if(answerIsTrue){
                score=Integer.parseInt(mScore.getText().toString());
                score+=2;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mScore.setText(String.valueOf(score));
                    }
                });
                Toast obj = Toast.makeText(GameOneActivity.this, R.string.judgment_toastTwo, Toast.LENGTH_SHORT);
                obj.show();
            }

            else
            if(answerIsTrue==false){
                score=Integer.parseInt(mScore.getText().toString());
                if(score>0)
                {score=score-1;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mScore.setText(String.valueOf(score));
                        }
                    });
                    }
                Toast obj = Toast.makeText(GameOneActivity.this, R.string.judgment_toast, Toast.LENGTH_SHORT);
                obj.show();

            }
            timer.cancel();
            timer.purge();
            timeLeft = 10;
            runTimer();
            mCurrentIndex = (mCurrentIndex + 1);}
        if(mCurrentIndex==7){
            timer.cancel();
            timer.purge();
            Intent intent = new Intent(GameOneActivity.this, ScoreActivity.class);
            String mssg=mScore.getText().toString();
            intent.putExtra("org.fatima.score",mssg);
            startActivity(intent);
        }
        updateQuestion();
    }
    public void updateQuestion() {

        if (mCurrentIndex<7){
            timer.cancel();
            timer.purge();
            timeLeft = 10;
            runTimer();
            int currentQuestionResId = Questions[mCurrentIndex].getQuestion();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mQuestion.setText(currentQuestionResId);
                    mScore.setText(String.valueOf(score));

                    int nb=mCurrentIndex+1;
                    mQuestionNb.setText("Question "+nb+" out of 7");
                }
            });

        }

    }
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mCurrentIndex);
        outState.putInt("score",score);
        outState.putInt("timeLeftToAnswer", timeLeft);
    }
}
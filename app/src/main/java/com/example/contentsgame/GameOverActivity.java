package com.example.contentsgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {
    TextView finalScore;
    Button returnButton;
    Button sendButton;
    String scoreText ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        finalScore = findViewById(R.id.finalScore);
        returnButton = findViewById(R.id.returnButton);
        sendButton = findViewById(R.id.sendGradesButton);
        changeScore();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scoreTwoIntent = new Intent(GameOverActivity.this, MainActivity.class);
                scoreTwoIntent.putExtra("score1.org.game",finalScore.getText().toString());
                startActivity(scoreTwoIntent);
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent impIntent = new Intent(Intent.ACTION_SEND);
                impIntent.setType("text/plain");
                String impScore = getResources().getString(R.string.I_scored_string_Guess) + " "+ scoreText;
                impIntent.putExtra(Intent.EXTRA_TEXT, impScore);
                startActivity(impIntent);
            }
        });

    }

    public void changeScore(){
        Intent intent = getIntent();
        scoreText = intent.getStringExtra("org.example.com.gameActivity.score");
        finalScore.setText(scoreText);
    }
}
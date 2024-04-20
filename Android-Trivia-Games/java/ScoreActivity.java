package com.example.contentsgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
private Button mScore;
private TextView mMssg;
private Button mExit;
private Button sendButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra("org.fatima.score");
        mScore = findViewById(R.id.final_score);
        mScore.setText(messageText);
        mMssg=findViewById(R.id.luck);
        mExit=findViewById(R.id.button3);
        sendButton2 = findViewById(R.id.sendGradesButton);

        if(Integer.parseInt(mScore.getText().toString())>7){
            mMssg.setText(R.string.good);


        }
        else{
            mMssg.setText(R.string.bad);
        }



        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scoreTwoIntent = new Intent(ScoreActivity.this, MainActivity.class);
                scoreTwoIntent.putExtra("score2.org.game", mScore.getText().toString());
                startActivity(scoreTwoIntent);
                finish();

            }
        });

        sendButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent impIntent = new Intent(Intent.ACTION_SEND);
                impIntent.setType("text/plain");
                String impScore = getResources().getString(R.string.I_scored_string_Continents) + " "+ mScore.getText().toString();
                impIntent.putExtra(Intent.EXTRA_TEXT, impScore);
                startActivity(impIntent);
            }
        });

    }

}
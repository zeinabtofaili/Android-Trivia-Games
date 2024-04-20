package com.example.contentsgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button gameOneButton;
    private Button gameTwoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gameOneButton=findViewById(R.id.gameOne);
        gameTwoButton=findViewById(R.id.gameTwo);

        gameOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent2);
            }
        });

        gameTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameOneActivity.class);
                startActivity(intent);

            }
        });

    }

}
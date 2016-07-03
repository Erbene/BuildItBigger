package com.erbene.jokerface;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by maia on 02/07/16.
 */
public class JokeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.joke_layout);
        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        TextView tv = (TextView) findViewById(R.id.joke_text);
        tv.setText(joke);
        super.onCreate(savedInstanceState);
    }
}

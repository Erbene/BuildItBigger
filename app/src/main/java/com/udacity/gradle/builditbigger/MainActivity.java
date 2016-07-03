package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.erbene.jokerface.JokeActivity;
import com.udacity.gradle.builditbigger.rest.GetJokeTask;


public class MainActivity extends ActionBarActivity implements GetJokeTask.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
//        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
//        pb.setVisibility(View.VISIBLE);
        GetJokeTask jokeTask = new GetJokeTask();
        jokeTask.execute(this);
    }

    @Override
    public void onJokeRetrieved(String joke) {
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra("joke", joke);
        startActivity(i);
//        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
//        pb.setVisibility(View.GONE);
    }
}

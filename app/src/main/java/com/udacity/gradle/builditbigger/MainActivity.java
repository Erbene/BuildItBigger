package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.erbene.jokerface.JokeActivity;
import com.udacity.gradle.builditbigger.rest.GetJokeTask;


public class MainActivity extends ActionBarActivity implements GetJokeTask.Callbacks {

    private RelativeLayout mProgressBar;
    private RelativeLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (RelativeLayout) findViewById(R.id.progress_layout);
        mMainLayout = (RelativeLayout) findViewById(R.id.main_layout);
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
        showProgress();
        GetJokeTask jokeTask = new GetJokeTask();
        jokeTask.execute(this);
    }

    @Override
    public void onJokeRetrieved(String joke) {
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra("joke", joke);
        startActivity(i);
        hideProgress();
    }
    private void hideProgress(){
        mProgressBar.setVisibility(View.GONE);
        mMainLayout.setVisibility(View.VISIBLE);

    }
    private void showProgress(){
        mProgressBar.setVisibility(View.VISIBLE);
        mMainLayout.setVisibility(View.GONE);

    }

}

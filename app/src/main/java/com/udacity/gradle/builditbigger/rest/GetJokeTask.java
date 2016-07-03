package com.udacity.gradle.builditbigger.rest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Pair;
import android.widget.Toast;

import com.erbene.jokerface.JokeActivity;
import com.example.maia.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by maia on 02/07/16.
 */
public class GetJokeTask extends AsyncTask<GetJokeTask.Callbacks, Void, String> {
    private static JokeApi myApiService = null;
    private Callbacks mListener;

    @Override
    protected String doInBackground(Callbacks... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        mListener = params[0];
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onJokeRetrieved(result);
    }
    public  interface Callbacks {
        void onJokeRetrieved(String joke);
    }
}

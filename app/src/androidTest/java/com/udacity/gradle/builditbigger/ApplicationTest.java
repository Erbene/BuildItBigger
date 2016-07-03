package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import com.udacity.gradle.builditbigger.rest.GetJokeTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements GetJokeTask.Callbacks {

    public ApplicationTest() {
        super(Application.class);
    }

    CountDownLatch signal;
    String mJoke;

    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new GetJokeTask().execute(this);
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("Retrieved joke is null", mJoke);
            assertFalse("joke is empty", mJoke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onJokeRetrieved(String joke) {
        mJoke = joke;
        signal.countDown();
    }
}
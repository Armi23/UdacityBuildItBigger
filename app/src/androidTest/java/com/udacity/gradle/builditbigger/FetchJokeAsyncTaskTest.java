package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test joke functionality
 */
public class FetchJokeAsyncTaskTest {
    @Test
    public void testFetchJokes() throws Exception {
        FetchJokeAsyncTask fetchJokeAsyncTask = new FetchJokeAsyncTask(InstrumentationRegistry.getContext());
        String result = fetchJokeAsyncTask.fetchJoke();

        assertNotNull(result);
        assertNotEquals(result.length(), 0);
    }
}
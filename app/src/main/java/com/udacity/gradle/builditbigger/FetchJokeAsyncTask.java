package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.armi.jokedisplaylibrary.JokeActivity;
import com.example.armi.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import timber.log.Timber;

/**
 * Task to fetch jokes and display them
 */
public class FetchJokeAsyncTask extends AsyncTask<Void, Void, String> {

    /**
     * Activity context to be used to launch joke activity
     */
    private Context context;

    /**
     * Constructor
     *
     * @param context context to launch joke activity
     */
    public FetchJokeAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        return fetchJoke();
    }

    @Override
    protected void onPostExecute(String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        context.startActivity(intent);
    }

    /**
     * Fetches jokes
     *
     * @return jokes fetched
     */
    protected String fetchJoke() {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://possible-symbol-166816.appspot.com/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        request.setDisableGZipContent(true);
                    }
                });
        MyApi myApi = builder.build();
        try {
            return myApi.getJoke().execute().getData();
        } catch (IOException e) {
            Timber.e(e, "Could not send request");
        }
        return "Could not get info";
    }
}

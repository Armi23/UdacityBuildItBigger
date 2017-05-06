package com.armi.jokedisplaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    /**
     * Joke string extra
     */
    public static final String EXTRA_JOKE = "extra_joke";

    /**
     * Text view to display jokes
     */
    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(EXTRA_JOKE);
        jokeTextView.setText(joke);
    }
}

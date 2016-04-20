package com.epicodus.bogglesolitare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();
    @Bind(R.id.playAgainButton) Button mPlayAgainButton;
    @Bind(R.id.wordsDisplay) TextView mWordsDisplay;
    @Bind(R.id.lettersDisplay) TextView mLettersDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<String> guessedWords = intent.getStringArrayListExtra("wordsGuessed");
        int score = guessedWords.size();
        String lettersGiven = intent.getStringExtra("lettersGiven");



        mWordsDisplay.setText("You made " + score + " words.Good job!");
        mLettersDisplay.setText("You used these letters: " + lettersGiven);

        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, BoggleActivity.class);
                startActivity(intent);
            }
        });
    }
}

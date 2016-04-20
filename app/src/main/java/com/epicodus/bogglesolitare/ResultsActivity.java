package com.epicodus.bogglesolitare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();
    @Bind(R.id.playAgainButton) Button mPlayAgainButton;
    @Bind(R.id.wordsDisplay) TextView mWordsDisplay;
    @Bind(R.id.lettersDisplay) TextView mLettersDisplay;
    @Bind(R.id.validWords) ListView mValidWordsList;
    @Bind(R.id.invalidWords) ListView mInvalidWordsList;
    private static ArrayList<String> mValidWords;
    private static ArrayList<String> mInvalidWords;
    private static ArrayList<String> mGuessedWords;
    private static String mLettersGiven;
    private static String[] mLettersGivenArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mGuessedWords = intent.getStringArrayListExtra("wordsGuessed");
        int score = mGuessedWords.size();
        mLettersGiven = intent.getStringExtra("lettersGiven");
        mLettersGivenArray = mLettersGiven.split(" ");

        evaluateWords();

        ArrayAdapter validAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mValidWords);
        mValidWordsList.setAdapter(validAdapter);
        ArrayAdapter invalidAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mInvalidWords);
        mInvalidWordsList.setAdapter(invalidAdapter);

        mWordsDisplay.setText("You made " + score + " words.Good job!");
        mLettersDisplay.setText("You used these letters: " + mLettersGiven);

        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, BoggleActivity.class);
                startActivity(intent);
            }
        });
    }

    private static void evaluateWords(){
        mValidWords = new ArrayList<String>();
        mInvalidWords = new ArrayList<String>();
        for (String word : mGuessedWords){
            String testWord = word.toUpperCase();
            for (int i = 0; i < mLettersGivenArray.length; i++){
                int index = testWord.indexOf(mLettersGivenArray[i]);
                if (index > -1){
                    testWord = testWord.replaceFirst(mLettersGivenArray[i], "");
                }
            }
            if (testWord.length() > 0){
                mInvalidWords.add(word);
            } else {
                mValidWords.add(word);
            }
        }

    }
}

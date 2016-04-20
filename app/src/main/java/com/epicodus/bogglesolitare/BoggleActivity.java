package com.epicodus.bogglesolitare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BoggleActivity extends AppCompatActivity {
    public static final String TAG = BoggleActivity.class.getSimpleName();
    private static ArrayList<Character> mAllGameLetters;
    private static ArrayList<Character> mSetLetters;
    private static ArrayList<String> mGuessedWords;
    @Bind(R.id.endRoundButton) Button mEndRoundButton;
    @Bind(R.id.addWordButton) Button mAddWordButton;
    @Bind(R.id.guessEdit) EditText mGuessEdit;
    @Bind(R.id.gameLetters) TextView mGameLetters;
    @Bind(R.id.guessWordList) ListView mGuessWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boggle);
        ButterKnife.bind(this);

        mGuessedWords = new ArrayList<String>();
        mSetLetters = new ArrayList<Character>;
        mSetLetters = generateGame();
        String letterOutput = letterOutputFormat(mSetLetters);

        mGameLetters.setText(letterOutput);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mGuessedWords);
        mGuessWordList.setAdapter(adapter);

        mAddWordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String guess = mGuessEdit.getText().toString();
                if(guess.length() > 2) {
                    mGuessedWords.add(guess);
                    mGuessEdit.setText("");
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(BoggleActivity.this, "# letters or more, please!", Toast.LENGTH_LONG).show();
                }
            }
        });

        mEndRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoggleActivity.this, ResultsActivity.class);
                intent.putExtra("wordsGuessed", mGuessedWords);
                intent.putExtra("lettersGiven", letterOutput);
                startActivity(intent);
            }
        });
    }
}

private ArrayList<Character> generateGame() {
    mAllGameLetters = new ArrayList<Character>();
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String vowels = "aeiou";
    mAllGameLetters.clear();
    Random r = new Random();
    for(int i = 0; i < 6; i++) {
        mAllGameLetters.add(alphabet.charAt(r.nextInt(alphabet.length())));
    }
    Random s = new Random();
    for(int j = 0; j < 2; j++) {
        mAllGameLetters.add(vowels.charAt(s.nextInt(vowels.length())));
    }
    return mAllGameLetters;
}

private String letterOutputFormat(ArrayList<Character> charArray) {
    String output = "";
    for(Character letter : charArray) {
        output = output + letter + " ";
    }
    return output.trim();
}


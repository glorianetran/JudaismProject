package com.judaismproject.gloriane.judaismproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    // TextViews
    TextView answer;
    TextView hurray;
    TextView scoreView;

    // Buttons
    Button nextQuestion;
    Button reset;

    // int
    int score;

    // intent
    Intent intent;

    // constants
    public static final String EXTRA_SCORE = "SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        // initialization
        answer = findViewById(R.id.answer);
        nextQuestion = findViewById(R.id.nextQuestion);
        hurray = findViewById(R.id.hurray);
        reset = findViewById(R.id.reset);
        scoreView = findViewById(R.id.currentscore);

        // reset
        reset.setEnabled(true);

        // grab intent and data
        intent = getIntent();
        String userAnswer = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String correctAnswer = intent.getStringExtra(MainActivity.EXTRA_CORRECT_ANSWER);
        score = intent.getIntExtra(MainActivity.EXTRA_MAIN_SCORE, 0);

        if(userAnswer.equals(correctAnswer)){
            hurray.setText("YOU GOT IT!");
            hurray.setTextColor(getResources().getColor(R.color.correct));
            score = score + 5;
            scoreView.setText("" + score);
        }else{
            hurray.setText("INCORRECT");
            hurray.setTextColor(getResources().getColor(R.color.wrong));
            score = score - 5;
            scoreView.setText("" + score);
        }

        answer.setText("ANSWER: " + correctAnswer);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                reset.setEnabled(false);
                scoreView.setText("" + 0);
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ANSWER ACTIVITY", "" + score);
                Intent toSendIntent = new Intent();
                toSendIntent.putExtra(EXTRA_SCORE, score);
                setResult(RESULT_OK, toSendIntent);
                finish();
            }
        });
    }
}

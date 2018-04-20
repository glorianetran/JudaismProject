package com.judaismproject.gloriane.judaismproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.judaismproject.gloriane.judaismproject.R;


public class AnswerFragment extends Fragment{

    // TextViews
    TextView answer;
    TextView hurray;
    TextView scoreView;

    // Buttons
    Button nextQuestion;
    Button reset;

    // int
    int theScore;

    // Strings
    String userAnswer;
    String correct;

    // constants
    public static final String EXTRA_SCORE = "SCORE";

    public AnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_answer, container, false);
        // initialization
        answer = v.findViewById(R.id.answer);
        nextQuestion = v.findViewById(R.id.nextQuestion);
        hurray = v.findViewById(R.id.hurray);
        reset = v.findViewById(R.id.reset);
        scoreView = v.findViewById(R.id.currentscore);

        // reset
        reset.setEnabled(true);

        //:TODO grab data from fragment class, data are 2  strings (userAnswer,correctAnswer, score)

        Bundle bundle = getArguments();
        userAnswer = bundle.getString(MainFragment.EXTRA_MESSAGE);
        correct = bundle.getString(MainFragment.EXTRA_CORRECT_ANSWER);
        theScore = bundle.getInt(MainFragment.EXTRA_MAIN_SCORE, 0);


        if(userAnswer.equals(correct)){
            hurray.setText("YOU GOT IT!");
            hurray.setTextColor(getResources().getColor(R.color.correct));
            theScore = theScore + 5;
            scoreView.setText("" + theScore);
        }else{
            hurray.setText("INCORRECT");
            hurray.setTextColor(getResources().getColor(R.color.wrong));
            theScore = theScore - 5;
            scoreView.setText("" + theScore);
        }

        answer.setText("ANSWER: " + correct);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theScore = 0;
                reset.setEnabled(false);
                scoreView.setText("" + 0);
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getTargetFragment().onActivityResult(
                        getTargetRequestCode(),
                        Activity.RESULT_OK,
                        new Intent().putExtra(EXTRA_SCORE, theScore)
                );

                getFragmentManager().popBackStack();
            }
        });

        return v;
}



}

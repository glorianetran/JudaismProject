package com.judaismproject.gloriane.judaismproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.judaismproject.gloriane.judaismproject.MainActivity;
import com.judaismproject.gloriane.judaismproject.R;


public class AnswerFragment extends Fragment{

    // TextViews
    TextView answer;
    TextView hurray;

    // Buttons
    Button nextQuestion;
    Button reset;

    // int
    int theScore;
    int questionNumber;

    // Strings
    String userAnswer;
    String correct;
    String explanation;

    // constants
    public static final String EXTRA_QUESTION = "NEXT QUESTION";

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setNavItemChecked(2);
        ((MainActivity)getActivity()).setTitle("Quiz");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_answer, container, false);
        // initialization
        answer = view.findViewById(R.id.answer);
        nextQuestion = view.findViewById(R.id.nextQuestion);
        hurray = view.findViewById(R.id.hurray);
        reset = view.findViewById(R.id.reset);

        // reset
        reset.setEnabled(true);

        Bundle bundle = getArguments();
        userAnswer = bundle.getString(MainFragment.EXTRA_MESSAGE);
        correct = bundle.getString(MainFragment.EXTRA_CORRECT_ANSWER);
        questionNumber = bundle.getInt(MainFragment.EXTRA_QUESTION_NUM, 0);
        explanation = bundle.getString(MainFragment.EXTRA_EXPLANATION);


        if(userAnswer.equals(correct)) {
            hurray.setText("YOU GOT IT!");
            hurray.setTextColor(getResources().getColor(R.color.correct));

            //array count - 1
            if (questionNumber < 16) {
                questionNumber++;
            } else{
                nextQuestion.setText("START OVER");
                reset.setEnabled(false);
                Snackbar.make(container, "You have finished all the questions!", Snackbar.LENGTH_LONG).show();
                questionNumber = 0;
            }
        }else{
            hurray.setText("INCORRECT");
            hurray.setTextColor(getResources().getColor(R.color.wrong));
            nextQuestion.setText("START OVER");
            Snackbar.make(container, "Unfortunately, you have to start over.", Snackbar.LENGTH_LONG).show();
            questionNumber = 0;
        }

        answer.setText("ANSWER: " + explanation);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view.findViewById(R.id.answer), "You have reset the questions.", Snackbar.LENGTH_LONG).show();
                questionNumber = 0;
                reset.setEnabled(false);
                nextQuestion.setText("START OVER");
            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTargetFragment().onActivityResult(
                        getTargetRequestCode(),
                        Activity.RESULT_OK,
                        new Intent().putExtra(EXTRA_QUESTION, questionNumber)
                );

                getFragmentManager().popBackStack();
            }
        });

        return view;
}


}

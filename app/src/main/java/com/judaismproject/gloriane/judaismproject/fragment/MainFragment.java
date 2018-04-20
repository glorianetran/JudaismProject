package com.judaismproject.gloriane.judaismproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.judaismproject.gloriane.judaismproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.app.Activity.RESULT_OK;



public class MainFragment extends Fragment{
    // Radio Buttons
    RadioGroup radioGroup;
    RadioButton radioButton;

    // Buttons
    Button submit;

    //TextViews
    TextView question;
    TextView scoreView;

    // ArrayList
    List<ArrayList<String>> dataList;


    // ints
    int theScore;
    int randomNum;

    // Ints
    int currentPosition = 0;



    // Constants
    public static final String EXTRA_MESSAGE = "ANSWER";
    public static final String EXTRA_CORRECT_ANSWER = "CORRECT ANSWER";
    public static final String EXTRA_MAIN_SCORE = "SCORE RETURNED";
    public static final int REQUEST_CODE = 1;
    public static final String VISIBLE_FRAGMENT = "visible_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        // initialization
        question = view.findViewById(R.id.question);
        radioGroup = view.findViewById(R.id.radiogroup);
        submit = view.findViewById(R.id.submit);
        dataList = new ArrayList<ArrayList<String>>();
        scoreView = view.findViewById(R.id.score);


        scoreView.setText("" + theScore);

        // data
        dataList.add(new ArrayList<String>(Arrays.asList("Do you like puppies?", "Obviously", "Is that even a question?", "They're adorable","Yes I do", "Obviously")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you like food?", "No", "Of course", "I need it to survive.", "Who doesn't", "Of course")));
        dataList.add(new ArrayList<String>(Arrays.asList("Which of these is not a fruit?","Watermelon", "Apple", "Lemon", "Durian", "Watermelon")));


        // Five fields: Question, Answer 1, Answer 2, Answer 3, Answer 4, Correct Answer, imageId

        // Populate the question

        //TODO: Go through array in order
        getNextQuestion();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = view.findViewById(selectedId);
                if(radioButton != null) {

                    String answer = (String) radioButton.getText();


                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRA_CORRECT_ANSWER, dataList.get(randomNum).get(5));
                    bundle.putString(EXTRA_MESSAGE, answer);
                    bundle.putInt(EXTRA_MAIN_SCORE, theScore);


                    AnswerFragment answerFrag = new AnswerFragment();
                    answerFrag.setTargetFragment(MainFragment.this, REQUEST_CODE);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    answerFrag.setArguments(bundle);


                    fragmentTransaction.replace(R.id.frame_content, answerFrag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else{
                    final Snackbar snackBar = Snackbar.make(view.findViewById(R.id.main), "Please enter an answer", Snackbar.LENGTH_LONG);

                    snackBar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackBar.dismiss();
                        }
                    });
                    snackBar.show();
                }

            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                theScore = data.getIntExtra(AnswerFragment.EXTRA_SCORE, 0);
                Log.d("MAINACTIVITY", "" + theScore);
                scoreView.setText("" + theScore);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getNextQuestion();
        radioGroup.clearCheck();
    }

    public void getNextQuestion(){
        Random rand = new Random();
        randomNum = rand.nextInt(dataList.size());
        final ArrayList<String> populatedArray = dataList.get(randomNum);

        for(int i = 0; i < radioGroup.getChildCount(); i++){
            RadioButton dynamicTextButton = (RadioButton) radioGroup.getChildAt(i);
            dynamicTextButton.setText(populatedArray.get(i+1));
        }

        question.setText(populatedArray.get(0));
    }

}

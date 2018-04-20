package com.judaismproject.gloriane.judaismproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
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

    // ImageView
    ImageView image;

    // ints
    int score;
    int randomNum;

    // Intent
    Intent intent;


    // Constants
    public static final String EXTRA_MESSAGE = "ANSWER";
    public static final String EXTRA_CORRECT_ANSWER = "CORRECT ANSWER";
    public static final String EXTRA_MAIN_SCORE = "SCORE RETURNED";
    public static final int SCORE_REQUEST = 1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        // initialization
        question = v.findViewById(R.id.question);
        radioGroup = v.findViewById(R.id.radiogroup);
        submit = v.findViewById(R.id.submit);
        dataList = new ArrayList<ArrayList<String>>();
        image = v.findViewById(R.id.picture);
        scoreView = v.findViewById(R.id.score);
        score = 0;


        // data
        dataList.add(new ArrayList<String>(Arrays.asList("Do you like puppies?", "Obviously", "Is that even a question?", "They're adorable","Yes I do", "Obviously", Integer.toString(R.drawable.corgi))));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you like food?", "No", "Of course", "I need it to survive.", "Who doesn't", "Of course", Integer.toString(R.drawable.food))));
        dataList.add(new ArrayList<String>(Arrays.asList("Which of these is not a fruit?","Watermelon", "Apple", "Lemon", "Durian", "Watermelon", Integer.toString(R.drawable.fruit))));


        // Five fields: Question, Answer 1, Answer 2, Answer 3, Answer 4, Correct Answer, imageId

        // Populate the question
        randomArrayList();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = v.findViewById(selectedId);


                String answer = (String) radioButton.getText();

                //Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT).show();

                // Use intent to send data to the Detail Activity
                intent.putExtra(EXTRA_MESSAGE, answer);
                intent.putExtra(EXTRA_CORRECT_ANSWER, dataList.get(randomNum).get(5));
                intent.putExtra(EXTRA_MAIN_SCORE, score);
                startActivityForResult(intent, SCORE_REQUEST);

            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCORE_REQUEST) {
            if (resultCode == RESULT_OK) {
                //score = data.getIntExtra(AnswerActivity.EXTRA_SCORE, 0);
                Log.d("MAINACTIVITY", "" + score);
                scoreView.setText("" + score);
                // Do something with the contact here (bigger example below)
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        randomArrayList();
        radioGroup.clearCheck();
    }

    public void randomArrayList(){
        Random rand = new Random();
        randomNum = rand.nextInt(dataList.size());
        final ArrayList<String> populatedArray = dataList.get(randomNum);

        for(int i = 0; i < radioGroup.getChildCount(); i++){
            RadioButton dynamicTextButton = (RadioButton) radioGroup.getChildAt(i);
            dynamicTextButton.setText(populatedArray.get(i+1));
        }

        question.setText(populatedArray.get(0));
        image.setImageResource(Integer.parseInt(populatedArray.get(6)));

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

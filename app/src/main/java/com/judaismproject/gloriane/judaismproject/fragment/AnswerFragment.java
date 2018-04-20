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
import android.widget.TextView;

import com.judaismproject.gloriane.judaismproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class AnswerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String param1, String param2) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        // grab intent and data
//        intent = getIntent();
//        String userAnswer = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        String correctAnswer = intent.getStringExtra(MainActivity.EXTRA_CORRECT_ANSWER);
//        score = intent.getIntExtra(MainActivity.EXTRA_MAIN_SCORE, 0);

//        if(userAnswer.equals(correctAnswer)){
//            hurray.setText("YOU GOT IT!");
//            hurray.setTextColor(getResources().getColor(R.color.correct));
//            score = score + 5;
//            scoreView.setText("" + score);
//        }else{
//            hurray.setText("INCORRECT");
//            hurray.setTextColor(getResources().getColor(R.color.wrong));
//            score = score - 5;
//            scoreView.setText("" + score);
//        }

//        answer.setText("ANSWER: " + correctAnswer);
//
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                score = 0;
//                reset.setEnabled(false);
//                scoreView.setText("" + 0);
//            }
//        });
//
//        nextQuestion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("ANSWER ACTIVITY", "" + score);
//                Intent toSendIntent = new Intent();
//                toSendIntent.putExtra(EXTRA_SCORE, score);
//                setResult(RESULT_OK, toSendIntent);
//                finish();
//            }
//        });


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
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

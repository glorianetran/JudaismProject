package com.judaismproject.gloriane.judaismproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.judaismproject.gloriane.judaismproject.MainActivity;
import com.judaismproject.gloriane.judaismproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment{
    // Radio Buttons
    RadioGroup radioGroup;
    RadioButton radioButton;

    // Buttons
    Button submit;

    //TextViews
    TextView question;

    // ArrayList
    List<ArrayList<String>> dataList;


    // ints
    int questionNumber;

    // Constants
    public static final String EXTRA_MESSAGE = "ANSWER";
    public static final String EXTRA_CORRECT_ANSWER = "CORRECT ANSWER";
    public static final String EXTRA_QUESTION_NUM = "QUESTION_NUMBER";
    public static final int REQUEST_CODE = 1;
    public static final String EXTRA_EXPLANATION = "EXPLANATION";

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
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        // initialization
        question = view.findViewById(R.id.question);
        radioGroup = view.findViewById(R.id.radiogroup);
        submit = view.findViewById(R.id.submit);
        dataList = new ArrayList<ArrayList<String>>();

        // data
        dataList.add(new ArrayList<String>(Arrays.asList("Is Japan a homogenous country?", "Yes", "No", "Maybe","I don’t know", "Yes, Japan is a homogenous country but there are still minority groups in it.","Yes")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think there are Jews currently living in Japan?", "Yes", "No", "Maybe", "I don’t know", "Yes there are Jews currently living in Japan.", "Yes")));
        dataList.add(new ArrayList<String>(Arrays.asList("Manga is a Japanese comic book/graphic novel. How popular do you think manga is in Japan?","Not popular at all", "Somewhat popular", "Popular", "Extremely popular", "Extremely popular. Manga is one of the most popular forms of mass entertainment in Japan.", "Extremely popular")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think manga has an influential role on the public?", "What? No way", "I don’t know maybe..?", "Somewhat influential","Very influential.", "Very influential. Manga affects behavior and social trends in Japan. For instance, tennis is a popular sport in Japan because of a popular tennis manga.","Very influential.")));
        dataList.add(new ArrayList<String>(Arrays.asList("What role did manga have in wartime Japan?", "It is was distraction from the war. ", "It was a form of propaganda.", "It was a cheap form of entertainment. ","All of the above. ", "It was a form of propaganda","It was a form of propaganda.")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think the Japanese government controlled manga content?", "Absolutely", "They have no right, so no.", "Somewhat controlled.","This question is irrelevant.", "Absolutely. There were requirements set by the government that manga artists had to follow.","Absolutely")));
        dataList.add(new ArrayList<String>(Arrays.asList("How are minorities treated in Japanese society?", "They are there, but ignored.", "They are considered nuisances to Japanese society.", "They are treated with the utmost respect.","They are used to promote Japan’s image of solidarity.", "They are used to promote Japan’s image of solidarity. In an article I read for this project, it mentions how Japan used African Americans for their own agenda.","They are used to promote Japan’s image of solidarity.")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think there is anti-semitism in Japan? ", "Yes", "No", "Maybe","I don’t know", "Yes, there are anti-semitic books circulating within Japan.", "Yes")));
        dataList.add(new ArrayList<String>(Arrays.asList("What was Japan’s reaction to the Allies lost in World War II?", "They were devastated that they lost.", "They were bitter against their enemies.", "They did not care and continued on with their lives.","They thought of themselves as victims.", "They thought of themselves as victims. Japan’s people thought that a natural disaster had occurred and they were victims.","They thought of themselves as victims.")));
        dataList.add(new ArrayList<String>(Arrays.asList("What does philosemitism mean?", "An over appreciation for Jews and their religion, often by gentiles.", "The opposite of anti-semitism?", "Loving Jews too much.","Another word for anti-semitism.", "An over appreciation for Jews and their religion, often by gentiles.", "An over appreciation for Jews and their religion, often by gentiles.")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think philosemitism occurs in Japan?", "Yes", "No", "Maybe","I don’t know", "Yes, after the Allies lost in World War II, Japan shifted from being anti-semitic to philo-semitic.","Yes")));
        dataList.add(new ArrayList<String>(Arrays.asList("Anne Frank’s diary plays a key role in Japanese society. What do you think that role is?", "It gives insight on the Holocaust for Japanese society.", "It is used by Japanese people to further their victim status.", "It helps Japanese people understand Jewish people.","It is a popular book read by young women in Japanese society.", "It is used by Japanese people to further their victim status. The book is extremely popular in Japan because Japanese people can relate to Anne Frank’s victim story.","It is used by Japanese people to further their victim status.")));
        dataList.add(new ArrayList<String>(Arrays.asList("In what other way does Anne Frank further Japan’s victimization beliefs?", "It gives Japanese people a way to learn about the Holocaust and World War II, without learning about Japan’s contributions during the war.", "It lets Japanese people imagine what it is like to be a victim during the war.", "It allows Japanese people to sympathize with the Jews.","There is no other way.", "It gives Japanese people a way to learn about the Holocaust and World War II, without learning about Japan’s contributions during the war.","It gives Japanese people a way to learn about the Holocaust and World War II, without learning about Japan’s contributions during the war.")));
        dataList.add(new ArrayList<String>(Arrays.asList("Do you think Japan tries to rewrite their history?", "No", "Maybe..?", "Somewhat","Yes", "Yes, Japan tries to rewrite their history instead of owning up to it. They like to rewrite it to adjust to what works to their advantage.","Yes")));
        dataList.add(new ArrayList<String>(Arrays.asList("How do you think Japan tried to rewrite their past?", "With media, of course.", "By brainwashing everyone with their technology.", "The government destroyed all evidence of their alliance with Germany during the war.","By not depicting themselves as allies with Germany during the war.", "With media, of course. Japan used literature, films, and television to preserve their victimization.","With media, of course.")));
        dataList.add(new ArrayList<String>(Arrays.asList("Besides Anne Frank’s Diary, how else do you think Japan tried to victimize themselves?", "They depicted the world as racist against them.", "They depicted themselves as a powerless country.", "They emphasized the Hiroshima and Nagasaki atomic bombs.", "They emphasized how Germany forced Japan to participate in its regime.", "They depicted themselves as a powerless country.","They emphasized the Hiroshima and Nagasaki atomic bombs.")));
        dataList.add(new ArrayList<String>(Arrays.asList("When do you think Japan came to a consensus that there were no longer going to write “distasteful” things about the war?", "1930s", "1940s", "1950s","1960s", "1950s","1950s")));

        // Five fields: Question, Answer 1, Answer 2, Answer 3, Answer 4, Correct Answer Plus Explanation

        // Populate the question
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
                    bundle.putString(EXTRA_CORRECT_ANSWER, dataList.get(questionNumber).get(6));
                    bundle.putString(EXTRA_EXPLANATION, dataList.get(questionNumber).get(5));
                    bundle.putString(EXTRA_MESSAGE, answer);
                    bundle.putInt(EXTRA_QUESTION_NUM, questionNumber);


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
                questionNumber = data.getIntExtra(AnswerFragment.EXTRA_QUESTION, 0);
            }
        }
    }


    public void getNextQuestion(){
        final ArrayList<String> populatedArray = dataList.get(questionNumber);

        for(int i = 0; i < radioGroup.getChildCount(); i++){
            RadioButton dynamicTextButton = (RadioButton) radioGroup.getChildAt(i);
            dynamicTextButton.setText(populatedArray.get(i+1));
        }

        question.setText(populatedArray.get(0));
    }

}

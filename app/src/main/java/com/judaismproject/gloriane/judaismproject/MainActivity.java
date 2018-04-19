package com.judaismproject.gloriane.judaismproject;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

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

    // Drawer Layout
    private DrawerLayout drawerLayout;


    // Constants
    public static final String EXTRA_MESSAGE = "ANSWER";
    public static final String EXTRA_CORRECT_ANSWER = "CORRECT ANSWER";
    public static final String EXTRA_MAIN_SCORE = "SCORE RETURNED";
    public static final int SCORE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // drawer
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        // initialization
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radiogroup);
        submit = findViewById(R.id.submit);
        dataList = new ArrayList<ArrayList<String>>();
        image = findViewById(R.id.picture);
        scoreView = findViewById(R.id.score);
        score = 0;

        // set Intent
        intent = new Intent(MainActivity.this, AnswerActivity.class);

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
                radioButton = findViewById(selectedId);


                String answer = (String) radioButton.getText();

                //Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT).show();

                // Use intent to send data to the Detail Activity
                intent.putExtra(EXTRA_MESSAGE, answer);
                intent.putExtra(EXTRA_CORRECT_ANSWER, dataList.get(randomNum).get(5));
                intent.putExtra(EXTRA_MAIN_SCORE, score);
                startActivityForResult(intent, SCORE_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCORE_REQUEST) {
            if (resultCode == RESULT_OK) {
                score = data.getIntExtra(AnswerActivity.EXTRA_SCORE, 0);
                Log.d("MAINACTIVITY", "" + score);
                scoreView.setText("" + score);
                // Do something with the contact here (bigger example below)
            }
        }
    }

    @Override
    protected void onResume() {
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
}

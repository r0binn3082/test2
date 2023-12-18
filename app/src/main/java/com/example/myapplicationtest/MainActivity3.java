package com.example.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.TextView;
import java.util.*;
/*
    *animals quiz
*/
public class MainActivity3 extends AppCompatActivity {
    private List<Question> questionsList = new ArrayList<>();
    // declare the text view for question
    private final TextView questionTextView = findViewById(R.id.question);
    //declare the options image buttons
    private final ImageButton optionButton1 = findViewById(R.id.option1);
    private ImageButton optionButton2 = findViewById(R.id.option2);
    private int currentIndexQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        questionsList.add(new Question("lapine", R.drawable.rabbit, R.drawable.lion, 1));
        questionsList.add(new Question("lionne", R.drawable.cat, R.drawable.lion, 2));
        questionsList.add(new Question("chienne", R.drawable.dog, R.drawable.monkey,1));
        questionsList.add(new Question("vache", R.drawable.cat, R.drawable.cow,2));
        questionsList.add(new Question("ânesse", R.drawable.monkey, R.drawable.donkey,2));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        displayQuestion();
    }

    public void displayQuestion() {
        Question currentQ = questionsList.get(currentIndexQ);
        questionTextView.setText(currentQ.getQuestionBody());
        optionButton1.setImageResource(currentQ.getResOption1());
        optionButton2.setImageResource(currentQ.getResOption2());
    }

    // hide the questions
    public void hideQuestions() {
        questionTextView.setVisibility(View.GONE);
        optionButton1.setVisibility(View.GONE);
        optionButton2.setVisibility(View.GONE);
    }

    //show the questions
    public void showQuestions() {
        questionTextView.setVisibility(View.VISIBLE);
        optionButton1.setVisibility(View.VISIBLE);
        optionButton2.setVisibility(View.VISIBLE);
    }

    public void startQuiz(View view) {
        // display gone to the strt button
        findViewById(R.id.startButton).setVisibility(View.GONE);
        // show the questions
        displayQuestion();
    }

    //calculate the score
    private void calculateScore() {
        int score = 0;

        SharedPreferences preferences = getSharedPreferences("user_answers", MODE_PRIVATE);

        for (Question question : questionsList) {
            String questionKey = question.getQuestionBody();
            int userAnswer = preferences.getInt(questionKey, -1); // -1 is a default value if the key is not found

            if (userAnswer == question.getCorrectAns()) {
                score++;
            }
        }
        hideQuestions();
        TextView finalScoreTextView = findViewById(R.id.finalScore);
        finalScoreTextView.setText("Your Score: " + score * 10);
        finalScoreTextView.setVisibility(View.VISIBLE);

    }

    // the action taken when clicking an option answer
    public void onClickOption(View view) {
        String selectedOption = view.getTag().toString();// need to be changed into integer
        //save the users answer in shared
        SharedPreferences preferences = getSharedPreferences("user_answers", MODE_PRIVATE); // creating the sharedprefrence object
        SharedPreferences.Editor editor = preferences.edit(); // create sharedprefrence editor to allow modification
        editor.putInt(questionsList.get(currentIndexQ).getQuestionBody(), Integer.parseInt(selectedOption));// the parsing function is to change the string to integer
        editor.apply();// to ensure the changes has been made
        if (currentIndexQ < questionsList.size() - 1) {
            currentIndexQ++;
            displayQuestion();
        } else {
            calculateScore();
        }

    }
}
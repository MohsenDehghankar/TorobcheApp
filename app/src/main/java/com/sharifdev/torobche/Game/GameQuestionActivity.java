package com.sharifdev.torobche.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sharifdev.torobche.Home;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.model.Question;

public class GameQuestionActivity extends AppCompatActivity {
    private int q_number = 1;
    private Button next;
    Handler handelr = new Handler();
    boolean goToNext = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_question);

        next = (Button) findViewById(R.id.next_question);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (q_number == 10) {
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);
                    //todo add to score
                    finish();
                }
                if (q_number < 10) {
                    //todo show correct answer
                    //todo save the user answer
                    //todo get random question
                    setQuestion(new Question());
                }
            }
        });

        setTimeBar();
    }

    /**
     * call it for each question (!!! set images no_photo if the question have not photo)
     */
    private void setQuestion(Question q) {
        fillComponent(R.id.q_image, q.questionImage, R.id.q_text, q.questionText);
        fillComponent(R.id.c1_image, q.image1, R.id.c1_text, q.answerText1);
        fillComponent(R.id.c2_image, q.image2, R.id.c2_text, q.answerText2);
        fillComponent(R.id.c3_image, q.image3, R.id.c3_text, q.answerText3);
        fillComponent(R.id.c4_image, q.image4, R.id.c4_text, q.answerText4);

        q_number += 1;

        setTimeBar();
    }

    private void fillComponent(int imageId, int imageResource, int textId, String text) {
        ImageView q_1_image = findViewById(imageId);
        q_1_image.setImageResource(imageResource);

        EditText q_1_text = findViewById(textId);
        q_1_text.setText(text);
    }

    private void setTimeBar() {
        final ProgressBar timeBar = findViewById(R.id.time_bar);

        //todo
    }
}
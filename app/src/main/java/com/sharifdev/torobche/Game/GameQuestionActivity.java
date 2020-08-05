package com.sharifdev.torobche.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sharifdev.torobche.Home;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.model.Question;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameQuestionActivity extends AppCompatActivity {
    private int q_number = 0;
    private Button next;
    boolean[] answers = new boolean[10];
    int h = 0;
    CountDownTimer mCountDownTimer;
    Question currentQuestion ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_question);

        setAnswer((ImageView) findViewById(R.id.imageView1));
        setAnswer((ImageView) findViewById(R.id.imageView2));
        setAnswer((ImageView) findViewById(R.id.imageView3));
        setAnswer((ImageView) findViewById(R.id.imageView4));

        next = (Button) findViewById(R.id.next_question);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCountDownTimer.cancel();
                if (q_number == 10) {
                    //todo show correct answer(not important now)
                    saveAnswer();
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);
                    calculateScore(5);  //todo add to user's score
                    finish();
                }
                if (q_number < 10) {
                    //todo show correct answer(not important now)
                    saveAnswer();
                    //todo get random question
                    currentQuestion = new Question();
                    setQuestion(currentQuestion);
                }
            }
        });

        Button like = (Button)findViewById(R.id.like_question);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestion.likes +=1;
            }
        });

        // todo get random question
        currentQuestion = new Question();
        setQuestion(currentQuestion);

    }

    private void saveAnswer(){
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.correct).getConstantState()){
            if(1 == currentQuestion.correctAnswer)
                answers[q_number-1] = true;
            return;
        }
        imageView = (ImageView) findViewById(R.id.imageView2);
        if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.correct).getConstantState()){
            if(2 == currentQuestion.correctAnswer)
                answers[q_number-1] = true;
            return;
        }
        imageView = (ImageView) findViewById(R.id.imageView3);
        if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.correct).getConstantState()){
            if(3 == currentQuestion.correctAnswer)
                answers[q_number-1] = true;
            return;
        }
        imageView = (ImageView) findViewById(R.id.imageView4);
        if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.correct).getConstantState()){
            if(4 == currentQuestion.correctAnswer)
                answers[q_number-1] = true;
        }
    }

    public void setAnswer(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.wrong).getConstantState()){
                    imageView.setImageResource(R.drawable.correct);
                }
                else{
                    imageView.setImageResource(R.drawable.wrong);
                }
            }
        });
    }

    /**
     * call it for each question ( set images to no_photo if the question have not photo)
     */
    private void setQuestion(Question q) {
        fillComponent(R.id.q_image, q.questionImage, R.id.q_text, q.getText());
        fillComponent(R.id.c1_image, q.image1, R.id.c1_text, q.answerText1);
        fillComponent(R.id.c2_image, q.image2, R.id.c2_text, q.answerText2);
        fillComponent(R.id.c3_image, q.image3, R.id.c3_text, q.answerText3);
        fillComponent(R.id.c4_image, q.image4, R.id.c4_text, q.answerText4);
        setDefaultImage((ImageView) findViewById(R.id.imageView1));
        setDefaultImage((ImageView) findViewById(R.id.imageView2));
        setDefaultImage((ImageView) findViewById(R.id.imageView3));
        setDefaultImage((ImageView) findViewById(R.id.imageView4));


        q_number += 1;

        setTimeBar();
    }

    private int calculateScore(int valueOfQ){
        int score = 0;
        for (int i =0 ;i< answers.length; i++){
            if(answers[i]){
                score += valueOfQ;
            }
        }
        return score;
    }

    private void setDefaultImage(ImageView image){
        image.setImageResource(R.drawable.wrong);
    }
    private void fillComponent(int imageId, int imageResource, int textId, String text) {
        ImageView image = findViewById(imageId);
        image.setImageResource(imageResource);

        TextView q_text = findViewById(textId);
        q_text.setText(text);
    }

    private void setTimeBar() {
        final ProgressBar timeBar = findViewById(R.id.time_bar);
        h = 0;
        timeBar.setProgress(h);
        mCountDownTimer=new CountDownTimer(10000,10) {

            @Override
            public void onTick(long millisUntilFinished) {
                h++;
                timeBar.setProgress((int)h*100/(10000/10));
            }

            @Override
            public void onFinish() {
                next.performClick();
                timeBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
    }
}
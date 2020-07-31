package com.sharifdev.torobche;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button cancel = (Button) findViewById(R.id.cancel_quiz);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final TextInputEditText name = findViewById(R.id.name_inp);
        final TextInputEditText time = findViewById(R.id.time_inp);
        final TextInputEditText numberOfQ =  findViewById(R.id.number_inp);

        Button next = findViewById(R.id.next_quiz);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText() == null || name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Name is Empty!!!", Toast.LENGTH_SHORT).show();
                }
                else if(time.getText() == null || time.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Time is Empty!!!", Toast.LENGTH_SHORT).show();
                }
                else if(numberOfQ.getText() == null || numberOfQ.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Number of Question is Empty!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //todo
                }
            }
        });
    }
}
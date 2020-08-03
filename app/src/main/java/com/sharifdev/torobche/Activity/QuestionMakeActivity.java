package com.sharifdev.torobche.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.backUtils.QuestionUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class QuestionMakeActivity extends AppCompatActivity {
    private boolean[] answer = new boolean[4];
    private ProgressBar progressBar;
    private ImageView[] choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        progressBar = findViewById(R.id.save_ques);

        choice = new ImageView[]{
                (ImageView) findViewById(R.id.imageView1),
                (ImageView) findViewById(R.id.imageView2),
                (ImageView) findViewById(R.id.imageView3),
                (ImageView) findViewById(R.id.imageView4)
        };

        setListeners((ImageView) findViewById(R.id.c1_image), 1);
        setListeners((ImageView) findViewById(R.id.c2_image), 2);
        setListeners((ImageView) findViewById(R.id.c3_image), 3);
        setListeners((ImageView) findViewById(R.id.c4_image), 4);
        setListeners((ImageView) findViewById(R.id.q_image), 5);

        final TextInputEditText topic = findViewById(R.id.topic_inp);

        Button save = (Button) findViewById(R.id.save_question);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = 0;
                int ans = 0;
                for (int i = 0; i < answer.length; i++) {
                    if (answer[i]) {
                        selected += 1;
                        ans = i;
                    }
                }
                if (topic.getText() == null || topic.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Topic is Empty!!!", Toast.LENGTH_SHORT).show();
                } else if (selected == 0) {
                    Toast.makeText(getApplicationContext(), "Select an answer", Toast.LENGTH_SHORT).show();
                } else if (selected > 1) {
                    Toast.makeText(getApplicationContext(), "Select Just One Answer", Toast.LENGTH_SHORT).show();
                } else {
                    // save question
                    progressBar.setVisibility(View.VISIBLE);
                    QuestionUtils.saveUserQuestion(
                            ((TextView) findViewById(R.id.q_text)).getText().toString(),
                            ((TextView) findViewById(R.id.c1_text)).getText().toString(),
                            ((TextView) findViewById(R.id.c2_text)).getText().toString(),
                            ((TextView) findViewById(R.id.c3_text)).getText().toString(),
                            ((TextView) findViewById(R.id.c4_text)).getText().toString(),
                            ((TextInputEditText) findViewById(R.id.topic_inp)).getText().toString(),
                            progressBar,
                            ans + 1
                    );
                    onBackPressed();
                }
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel_question);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        for (int i = 0; i < choice.length; i++) {
            setAnswer(choice[i], i + 1);
        }

    }

    private void setListeners(ImageView imageView, final int requestCode) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, requestCode);
            }
        });

    }

    public void setAnswer(final ImageView imageView, final int a) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (ImageView imageView1 : choice) {
                    imageView1.setImageResource(R.drawable.wrong);
                }
                if (imageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.wrong).getConstantState()) {
                    imageView.setImageResource(R.drawable.correct);
                    for (int i = 0; i < answer.length; i++) {
                        answer[i] = false;
                    }
                    answer[a - 1] = true;
                } else {
                    imageView.setImageResource(R.drawable.wrong);
                    answer[a - 1] = false;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageView imageView;
                switch (requestCode) {
                    case 1:
                        imageView = (ImageView) findViewById(R.id.c1_image);
                        break;
                    case 2:
                        imageView = (ImageView) findViewById(R.id.c2_image);
                        break;
                    case 3:
                        imageView = (ImageView) findViewById(R.id.c3_image);
                        break;
                    case 4:
                        imageView = (ImageView) findViewById(R.id.c4_image);
                        break;
                    case 5:
                        imageView = (ImageView) findViewById(R.id.q_image);
                        break;
                    default:
                        imageView = new ImageView(getApplicationContext());

                }
                imageView.setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

}
package com.sharifdev.torobche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class QuestionMakeActivity extends AppCompatActivity {
    private boolean[] answer = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

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
                if(topic.getText() == null || topic.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Topic is Empty!!!", Toast.LENGTH_SHORT).show();
                }
                else if(!answer[0] && !answer[1] && !answer[2] && !answer[3]){
                    Toast.makeText(getApplicationContext(),"Select an answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    // todo
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

        setAnswer((ImageView) findViewById(R.id.imageView1) , 1);
        setAnswer((ImageView) findViewById(R.id.imageView2) , 2);
        setAnswer((ImageView) findViewById(R.id.imageView3) , 3);
        setAnswer((ImageView) findViewById(R.id.imageView4) , 4);

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

    public void setAnswer(final ImageView imageView, final int a){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageView.getDrawable().getConstantState() ==getResources().getDrawable(R.drawable.wrong).getConstantState()){
                    imageView.setImageResource(R.drawable.correct);
                    answer[a-1] = true;
                }
                else{
                    imageView.setImageResource(R.drawable.wrong);
                    answer[a-1] = false;
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
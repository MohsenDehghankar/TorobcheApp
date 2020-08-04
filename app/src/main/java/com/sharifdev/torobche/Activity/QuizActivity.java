package com.sharifdev.torobche.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.backUtils.QuizUtils;
import com.sharifdev.torobche.backUtils.UserUtils;
import com.sharifdev.torobche.model.Quiz;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    final ArrayList<String> usernames = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //
        progressBar = findViewById(R.id.user_add_loading);
        progressBar.bringToFront();
        setUsernameView();
        //

        Button cancel = (Button) findViewById(R.id.cancel_quiz);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final TextInputEditText name = findViewById(R.id.name_inp);
        final TextInputEditText time = findViewById(R.id.time_inp);

        Button next = findViewById(R.id.next_quiz);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText() == null || name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Name is Empty!!!", Toast.LENGTH_SHORT).show();
                } else if (time.getText() == null || time.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Time is Empty!!!", Toast.LENGTH_SHORT).show();
                } else if (usernames.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Add a Username", Toast.LENGTH_SHORT).show();
                } else {
                    //todo create quiz
                    progressBar.setVisibility(View.VISIBLE);
                    QuizUtils.addQuiz(name.getText().toString(),
                            time.getText().toString(),
                            new FunctionCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    if (e != null)
                                        e.printStackTrace();
                                    progressBar.setVisibility(View.GONE);
                                    onBackPressed();
                                }
                            },
                            usernames
                    );
                }
            }
        });
    }

    private void setUsernameView() {
        final Button btn = findViewById(R.id.add_username);
        final TextInputEditText inputEditText = findViewById(R.id.username_inp_quiz_text);
        final ChipGroup chipGroup = findViewById(R.id.mainTagChipGroup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Chip chip = ((Chip) getLayoutInflater().inflate(R.layout.chip_username, chipGroup, false));
                addUsername(chip, inputEditText, chipGroup);
            }
        });

        inputEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    final Chip chip = ((Chip) getLayoutInflater().inflate(R.layout.chip_username, chipGroup, false));
                    addUsername(chip, inputEditText, chipGroup);
                    return true;
                }
                return false;
            }
        });
    }

    private void addUsername(final Chip chip, final TextInputEditText inputEditText,
                             final ChipGroup chipGroup) {
        progressBar.setVisibility(View.VISIBLE);
        String user = inputEditText.getText().toString();
        UserUtils.isUserAvailable(user, new FunctionCallback<Boolean>() {
            @Override
            public void done(Boolean object, ParseException e) {
                progressBar.setVisibility(View.GONE);
                if (object) {
                    HorizontalScrollView scrollView = findViewById(R.id.scroll_group);
                    if (inputEditText.getText().toString().isEmpty())
                        return;
                    chip.setText(inputEditText.getText().toString());
                    usernames.add(inputEditText.getText().toString());
                    chip.setCheckable(false);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            usernames.remove(chip.getText().toString());
                            chipGroup.removeView(chip);
                        }
                    });
                    chipGroup.addView(chip);
                    scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    inputEditText.setText("");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Username Invalid!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                    inputEditText.setText("");
                }
            }
        });
    }
}
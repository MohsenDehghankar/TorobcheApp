package com.sharifdev.torobche.backUtils;

import android.view.View;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;

/**
 * Table of questions in Backend
 */
public class QuestionUtils {
    public static void saveUserQuestion(final String questionText, String choice1Text,
                                        String choice2Text,
                                        String choice3Text,
                                        String choice4Text,
                                        String categoryName,
                                        final ProgressBar progressBar,
                                        final int answer) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("questionText", questionText);
        params.put("choice1Text", choice1Text);
        params.put("choice2Text", choice2Text);
        params.put("choice3Text", choice3Text);
        params.put("choice4Text", choice4Text);
        params.put("answer", String.valueOf(answer));

        // get the category
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Category");
        query.whereEqualTo("name", categoryName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null)
                    e.printStackTrace();
                else {
                    params.put("topic", objects.get(0).getObjectId());
                    // call function
                    try {
                        Object userQuestion = ParseCloud.callFunction("save_user_question", params);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}

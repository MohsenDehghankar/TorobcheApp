package com.sharifdev.torobche.backUtils;

import com.google.gson.Gson;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizUtils {
    public static void addQuiz(String quizName, String time,
                               FunctionCallback<ParseObject> callback,
                               ArrayList<String> usernames) {
        Gson gson = new Gson();
        HashMap<String, String> params = new HashMap<>();
        params.put("name", quizName);
        params.put("time", time);
        params.put("users", gson.toJson(usernames.toArray(new String[]{})));
        ParseCloud.callFunctionInBackground("add_quiz", params, callback);
    }

    public static void getQuizes(FunctionCallback<List<ParseObject>> callback) {
        HashMap<String, String> params = new HashMap<>();
        ParseCloud.callFunctionInBackground("get_user_quizes", params, callback);
    }
}

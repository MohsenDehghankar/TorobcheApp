package com.sharifdev.torobche.backUtils;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.sharifdev.torobche.Home;
import com.sharifdev.torobche.HomeFragment;
import com.sharifdev.torobche.R;

import java.util.HashMap;

/**
 * Work with user table in Backend
 */
public class UserUtils {
    public static void changeProfile(int newId, final Fragment fragment, final View view) {
        int avatarId = newId + 1;
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.put("avatar_id", avatarId);
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    e.printStackTrace();
                else
                    ((HomeFragment) fragment).loadUserData(view);
            }
        });
    }

    public static void isUserAvailable(String user, FunctionCallback<Boolean> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", user);
        ParseCloud.callFunctionInBackground("does_user_exists", params, callback);
    }

    public static void addPoint(int point) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.put("level", ((int) currentUser.get("level")) + point);
        currentUser.saveInBackground();
    }
}

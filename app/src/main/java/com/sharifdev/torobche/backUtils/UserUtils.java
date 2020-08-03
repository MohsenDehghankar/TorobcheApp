package com.sharifdev.torobche.backUtils;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.sharifdev.torobche.Home;
import com.sharifdev.torobche.HomeFragment;
import com.sharifdev.torobche.R;

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
}

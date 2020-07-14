package com.sharifdev.torobche;

import android.app.Application;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("first");
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KzyaJzgW3mW7sxJX") // should correspond to APP_ID env variable
                .server("https://195.248.241.235:1337/parse/").build());

        /*System.out.println("second");
        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 4444);
        gameScore.put("playerName", "Mohsen Dehghankar2222");
        gameScore.put("cheatMode", true);

        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    System.out.println("yesssssss");
                } else {
                    System.out.println("noooo");
                    ex.printStackTrace();
                }
            }
        });*/

    }
}

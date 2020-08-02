package com.sharifdev.torobche.backUtils;

import android.app.Activity;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Work with categories table in Backend
 */
public class CategoryUtils {
    public static void getAllCategories(final ArrayList<SelectCategoryActivity.HolderClass> holderClasses,
                                        final SelectCategoryActivity activity) {
        final HashMap<String, Integer> categories = new HashMap<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null)
                    e.printStackTrace();
                else {
                    for (ParseObject parseObject : objects) {
                        categories.put(((String) parseObject.get("name")), ((Integer) parseObject.get("icon_id")));
                        // todo category image
                        holderClasses.add(new SelectCategoryActivity.HolderClass(
                                ((String) parseObject.get("name")),
                                getCategoryImageByID(parseObject.getInt("icon_id"))
                        ));
                    }
                    // do other things for holder
                    activity.setUpHolders();
                    // stop progress bar
                    activity.progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    private static int getCategoryImageByID(int id) {
        switch (id) {
            case 1:
                return R.drawable.ic_technology;
            case 2:
                return R.drawable.ic_sport;
            case 3:
                return R.drawable.ic_games;
            case 4:
                return R.drawable.ic_history;
            case 5:
                return R.drawable.ic_food;
            case 6:
                return R.drawable.ic_education;
            case 7:
                return R.drawable.ic_business;
            case 8:
                return R.drawable.ic_tv;
            case 9:
                return R.drawable.ic_music;
            default:
                return R.drawable.ic_general;
        }
    }
}

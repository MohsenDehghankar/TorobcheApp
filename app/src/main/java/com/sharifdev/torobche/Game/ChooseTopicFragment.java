package com.sharifdev.torobche.Game;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.sharifdev.torobche.Category.CategoryAdapter;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.HomeFragment;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.backUtils.CategoryUtils;
import com.sharifdev.torobche.backUtils.QuizUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseTopicFragment extends HomeFragment {
    private ArrayList<SelectCategoryActivity.HolderClass> holderClasses;
    private ProgressBar progressBar;
    private List<ParseObject> cats;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_topic, container, false);

        progressBar = rootView.findViewById(R.id.load_topics);
        getUsersTopics(rootView);

        return rootView;
    }

    public void getUsersTopics(View view) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        try {
            cats = currentUser.getList("FavoriteCategories");
            CategoryUtils.getCategoriesByPointer(cats, this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initCategoryView(View rootView, final List<ParseObject> cats, boolean loading) {
        holderClasses = new ArrayList<>();

        for (ParseObject cat : cats) {
            holderClasses.add(new SelectCategoryActivity.HolderClass(
                    ((String) cat.get("name")),
                    CategoryUtils.getCategoryImageByID(cat.getInt("icon_id"))
            ));
        }
        GridView gridView = (GridView) rootView.findViewById(R.id.topic_select_view);
        gridView.setAdapter(new CategoryAdapter(holderClasses, getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // go to next page (create quiz)
                QuizUtils.createSinglePlayer(cats.get(i).getString("name"),
                        new FunctionCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                // go nxt
//                                getActivity().getSupportFragmentManager().popBackStack();

                                Intent intent = new Intent(getContext(), GameQuestionActivity.class);
                                intent.putExtra("type", "single");
                                startActivity(intent);

                            }
                        });
            }
        });

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }
}

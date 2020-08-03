package com.sharifdev.torobche.Game;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.sharifdev.torobche.Category.CategoryAdapter;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.R;

import java.util.ArrayList;

public class ChooseTopicFragment  extends Fragment {
    private ArrayList<SelectCategoryActivity.HolderClass> holderClasses;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_topic, container, false);

        //todo set user's topics
        holderClasses = new ArrayList<>();
        holderClasses.add(new SelectCategoryActivity.HolderClass("a",R.drawable.clock));

        GridView gridView = (GridView) rootView.findViewById(R.id.topic_select_view);
        gridView.setAdapter(new CategoryAdapter(holderClasses, getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //todo go to next page
            }
        });
        return rootView;
    }
}

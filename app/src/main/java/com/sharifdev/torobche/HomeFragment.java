package com.sharifdev.torobche;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment {
    private static RecyclerView category_view;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ImageView> categoryDataSet = new ArrayList<>();
    private Button add_category;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        initCategoryView(rootView, container.getContext());

        // todo add button
        //add_category =(Button) rootView.findViewById(R.id.);
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectCategoryActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void initCategoryView(View rootView, Context context){
        category_view = (RecyclerView) rootView.findViewById(R.id.category_recyclerView);
        category_view.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        category_view.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        for(int i= 0; i< 10; i++) {
            // todo read
            categoryDataSet.add(createImageView(context));
        }
        mAdapter = new CategoryRecyclerViewAdapter(categoryDataSet);
        category_view.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    private ImageView createImageView(Context context){
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.chat);
        return imageView;
    }

}

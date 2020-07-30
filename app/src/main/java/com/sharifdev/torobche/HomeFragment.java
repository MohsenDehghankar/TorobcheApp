package com.sharifdev.torobche;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<SelectCategoryActivity.CategoryClass> categoryDataSet = new ArrayList<>();
    private Button add_category;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        initCategoryView(rootView, container.getContext());

        add_category =(Button) rootView.findViewById(R.id.add_category);
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
        layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_view.setLayoutManager(layoutManager);

        // todo
        for(int i = 0 ; i< 10 ; i ++)
            categoryDataSet.add(new SelectCategoryActivity.CategoryClass("android", R.drawable.ic_launcher_background));

        mAdapter = new CategoryRecyclerViewAdapter(getContext(),categoryDataSet);
        category_view.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}

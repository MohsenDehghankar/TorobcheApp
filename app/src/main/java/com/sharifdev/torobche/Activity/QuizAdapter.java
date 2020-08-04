package com.sharifdev.torobche.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.sharifdev.torobche.Activity.QuizActivity;
import com.sharifdev.torobche.Category.CategoryRecyclerViewAdapter;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.R;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<ImageHolder> {

    private final List<SelectCategoryActivity.HolderClass> mValues;
    private Context context;

    public QuizAdapter(Context context, List<SelectCategoryActivity.HolderClass> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view;
        if (viewType == R.layout.single_image_layout) {
            view = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_image_layout, parent, false);
        } else {
            view = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.add_button, parent, false);
        }
        return new ImageHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        return (position == mValues.size()) ? R.layout.add_button_layout : R.layout.single_image_layout;
    }
    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    @Override
    public void onBindViewHolder(final ImageHolder holder, final int position) {
        if (position == mValues.size()) {
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuizActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            holder.mImageView.setImageResource(mValues.get(position).image);
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

}

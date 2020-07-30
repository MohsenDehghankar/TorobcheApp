package com.sharifdev.torobche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private final List<Integer> mValues;
    private Context context;

    public HistoryAdapter(Context context, List<Integer> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view;
        view = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_image_layout, parent, false);

        return new CategoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryRecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.mImageView.setImageResource(mValues.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}

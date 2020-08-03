package com.sharifdev.torobche.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.sharifdev.torobche.Activity.HistoryActivity;
import com.sharifdev.torobche.Category.CategoryRecyclerViewAdapter;
import com.sharifdev.torobche.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<ImageHolder> {

    private final List<Integer> mValues;
    private Context context;

    public HistoryAdapter(Context context, List<Integer> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view;
        view = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_image_layout, parent, false);

        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageHolder holder, final int position) {

        holder.mImageView.setImageResource(mValues.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryActivity.class);
                intent.putExtra("history_number", mValues.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}

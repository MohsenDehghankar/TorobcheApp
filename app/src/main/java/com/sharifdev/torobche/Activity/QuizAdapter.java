package com.sharifdev.torobche.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharifdev.torobche.Activity.QuizActivity;
import com.sharifdev.torobche.Category.CategoryRecyclerViewAdapter;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.R;
import com.sharifdev.torobche.model.Quiz;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    private final List<Quiz> mValues;
    private Context context;

    public QuizAdapter(Context context, List<Quiz> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view;
        if (viewType == R.layout.single_image_layout) {
            view = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.history_card, parent, false);
        } else {
            view = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.add_button_layout, parent, false);
        }
        return new QuizHolder(view);
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
    public void onBindViewHolder(@NotNull final QuizHolder holder, final int position) {
        if (position == mValues.size()) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, QuizActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            holder.imageView.setImageResource(R.drawable.history);
            holder.name.setText(mValues.get(position).getName());
            holder.time.setText("Time: " + String.valueOf(mValues.get(position).getTime()));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // todo show quiz details and edit
                }
            });
        }
    }

    class QuizHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView time;

        public QuizHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.topic_image1);
            name = itemView.findViewById(R.id.topic_name1);
            time = itemView.findViewById(R.id.point1);
            if (imageView == null) {
                imageView = itemView.findViewById(R.id.select_category_item_image);
            }
        }
    }

}

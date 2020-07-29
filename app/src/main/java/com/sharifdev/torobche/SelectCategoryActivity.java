package com.sharifdev.torobche;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SelectCategoryActivity extends AppCompatActivity {
    public static ArrayList<CategoryClass> categoryClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category_activity);

        GridView gridView = findViewById(R.id.category_select_view);
        gridView.setAdapter(new CategoryAdapter(categoryClasses));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // todo
            }
        });

    }

    public static class CategoryClass {
        String name;
        int image;
    }

    public class CategoryAdapter extends BaseAdapter {
        private final List<CategoryClass> items;

        public CategoryAdapter(List<CategoryClass> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            TextView textView;

            if (view == null) {
                view = View.inflate(viewGroup.getContext(), R.layout.select_vategory_item, viewGroup);
            }

            imageView = (ImageView) view.findViewById(R.id.select_category_item_image);
            textView = (TextView) view.findViewById(R.id.select_category_item_text);
            imageView.setImageResource(items.get(i).image);
            textView.setText(items.get(i).name);
            return view;
        }
    }
}



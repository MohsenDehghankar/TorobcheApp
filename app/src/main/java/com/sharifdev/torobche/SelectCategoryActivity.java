package com.sharifdev.torobche;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SelectCategoryActivity extends AppCompatActivity {
    public static ArrayList<CategoryClass> categoryClasses;
    private ArrayList<CategoryClass> selectedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category_activity);

        selectedCategory = new ArrayList<>();
        categoryClasses = new ArrayList<>();
        categoryClasses.add(new CategoryClass("android" , R.drawable.ic_launcher_background));
        categoryClasses.add(new CategoryClass("android" , R.drawable.ic_launcher_foreground));

        GridView gridView = findViewById(R.id.category_select_view);
        gridView.setAdapter(new CategoryAdapter(categoryClasses));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                view.findViewById(R.id.select_category_item_image).setBackground(highlight);
                selectedCategory.add(categoryClasses.get(i));
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel_category);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Button save = (Button) findViewById(R.id.save_category);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // todo save selected array in account
                finish();
            }
        });

    }

    public static class CategoryClass {
        String name;
        int image;

        CategoryClass(String name, int image){
            this.image = image;
            this.name = name;
        }
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
                LayoutInflater inflter = (LayoutInflater.from(getApplicationContext()));
                view = inflter.inflate(R.layout.select_vategory_item, null);
            }

            imageView = (ImageView) view.findViewById(R.id.select_category_item_image);
            textView = (TextView) view.findViewById(R.id.select_category_item_text);
            imageView.setImageResource(items.get(i).image);
            textView.setText(items.get(i).name);
            return view;
        }
    }
}



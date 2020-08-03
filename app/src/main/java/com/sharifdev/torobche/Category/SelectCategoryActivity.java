package com.sharifdev.torobche.Category;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sharifdev.torobche.R;
import com.sharifdev.torobche.backUtils.CategoryUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectCategoryActivity extends AppCompatActivity {
    public static ArrayList<HolderClass> holderClasses;
    private ArrayList<HolderClass> selectedCategory;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category_activity);

        this.progressBar = findViewById(R.id.category_loading);
        progressBar.setVisibility(View.VISIBLE);

        selectedCategory = new ArrayList<>();
        holderClasses = new ArrayList<>();
        //holderClasses.add(new HolderClass("android" , R.drawable.ic_launcher_background));
        //holderClasses.add(new HolderClass("android" , R.drawable.ic_launcher_foreground));
        addCategories(holderClasses);

        Button cancel = (Button) findViewById(R.id.cancel_question);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // save selected categories
        Button save = (Button) findViewById(R.id.save_question);
        save.setOnClickListener(new CategoryUtils.SaveCategoryOnClockListener(this));

    }

    private void addCategories(ArrayList<HolderClass> holderClasses) {
        CategoryUtils.getAllCategories(holderClasses, this);
    }

    public void setUpHolders() {
        GridView gridView = findViewById(R.id.category_select_view);
        gridView.setAdapter(new CategoryAdapter(holderClasses));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                view.findViewById(R.id.select_category_item_image).setBackground(highlight);
                selectedCategory.add(holderClasses.get(i));
            }
        });
    }

    public static class HolderClass {
        public String name;
        public int image;

        public HolderClass(String name, int image) {
            this.image = image;
            this.name = name;
        }
    }

    public class CategoryAdapter extends BaseAdapter {
        private final List<HolderClass> items;

        public CategoryAdapter(List<HolderClass> items) {
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
                view = inflter.inflate(R.layout.select_category_item, null);
            }

            imageView = (ImageView) view.findViewById(R.id.select_category_item_image);
            textView = (TextView) view.findViewById(R.id.select_category_item_text);
            imageView.setImageResource(items.get(i).image);
            textView.setText(items.get(i).name);
            return view;
        }
    }

    public ArrayList<HolderClass> getSelectedCategory() {
        return selectedCategory;
    }
}



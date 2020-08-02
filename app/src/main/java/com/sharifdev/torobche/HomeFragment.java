package com.sharifdev.torobche;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.parse.ParseUser;
import com.sharifdev.torobche.Adapters.ProfileChooseArrayAdapter;
import com.sharifdev.torobche.Category.CategoryRecyclerViewAdapter;
import com.sharifdev.torobche.Category.SelectCategoryActivity;
import com.sharifdev.torobche.model.ProfileAvatar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static RecyclerView category_view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SelectCategoryActivity.HolderClass> categoryDataSet = new ArrayList<>();
    private Button add_category;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initCategoryView(rootView, container.getContext());
        loadUserData(rootView);
        changeProfileAvatarView(rootView);
        return rootView;
    }

    public void loadUserData(View view) {
        final ParseUser user = ParseUser.getCurrentUser();
        final ImageView profile = view.findViewById(R.id.profile_avatar);
        profile.setImageDrawable(view.getResources().getDrawable(ProfileAvatar
                .getAvatarResourceId(((int) user.get("avatar_id")))));
        TextView username = view.findViewById(R.id.user_id);
        username.setText("Hi " + user.getUsername() + "!");
        // todo: update group name
        TextView level = view.findViewById(R.id.statistic);
        try {
            level.setText(getString(R.string.lev) + ((int) user.get("level")));
        } catch (NullPointerException e) {
            level.setText(R.string.lev_na);
        }
    }

    private void changeProfileAvatarView(View view) {
        ImageView profile = view.findViewById(R.id.profile_avatar);
        profile.setOnClickListener(new ProfileAvatar
                .ChangeAvatarBtnListener(getContext(), getActivity(), this, view));
    }

    private void initCategoryView(View rootView, Context context) {
        category_view = (RecyclerView) rootView.findViewById(R.id.category_recyclerView);
        category_view.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_view.setLayoutManager(layoutManager);

        // todo
        for (int i = 0; i < 10; i++)
            categoryDataSet.add(new SelectCategoryActivity.HolderClass("android" + i, R.drawable.ic_launcher_background));

        mAdapter = new CategoryRecyclerViewAdapter(getContext(), categoryDataSet);
        category_view.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}

package com.sharifdev.torobche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> imageIds = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);

        //todo
        names.add("zahra");
        imageIds.add(R.drawable.plus);

        names.add("zizi");
        imageIds.add(R.drawable.plus);

        ListView userList = rootView.findViewById(R.id.user_list);
        ChatAdapter chatAdapter = new ChatAdapter(getContext(), imageIds, names);
        userList.setAdapter(chatAdapter);


        MaterialSearchBar searchBar = rootView.findViewById(R.id.searchBar);

        return rootView;
    }
}
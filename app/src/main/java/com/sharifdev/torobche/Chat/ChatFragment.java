package com.sharifdev.torobche.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sharifdev.torobche.Chat.ChatAdapter;
import com.sharifdev.torobche.R;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> imageIds = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);

        //todo get contacts from server
        names.add("zahra");
        imageIds.add(R.drawable.plus);

        names.add("zizi");
        imageIds.add(R.drawable.plus);

        RecyclerView userList = rootView.findViewById(R.id.user_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        userList.setLayoutManager(layoutManager);
        ChatAdapter chatAdapter = new ChatAdapter(getContext(), imageIds, names);
        userList.setAdapter(chatAdapter);


        MaterialSearchBar searchBar = rootView.findViewById(R.id.searchBar);

        return rootView;
    }
}
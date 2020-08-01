package com.sharifdev.torobche.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import com.sharifdev.torobche.R;
import com.sharifdev.torobche.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String username = Objects.requireNonNull(getIntent().getExtras()).getString("username");
        int imageResource =  Objects.requireNonNull(getIntent().getExtras()).getInt("image");


        //todo
        messages.add(new Message("hello", username, imageResource));
        messages.add(new Message("hi!!!, how are you???", "Sara", R.drawable.no_photo));

        RecyclerView messageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        MessageListAdapter messageAdapter = new MessageListAdapter(this, messages);
        //todo
        messageAdapter.setCurrentUserName(username);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setAdapter(messageAdapter);

    }

}
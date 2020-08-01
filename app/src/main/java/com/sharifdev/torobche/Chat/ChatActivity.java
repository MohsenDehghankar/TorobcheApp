package com.sharifdev.torobche.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sharifdev.torobche.R;
import com.sharifdev.torobche.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    String username;
    int imageResource;
    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        username = Objects.requireNonNull(getIntent().getExtras()).getString("username");
        imageResource =  Objects.requireNonNull(getIntent().getExtras()).getInt("image");

        //todo get messages from server
        messages.add(new Message("hello", username, imageResource));
        for(int i = 0;i<20;i++)
        messages.add(new Message("hi!!!, how are you???", "Sara", R.drawable.no_photo));

        RecyclerView messageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        final MessageListAdapter messageAdapter = new MessageListAdapter(this, messages);
        //todo get current user = login user
        messageAdapter.setCurrentUserName(username);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setAdapter(messageAdapter);

        final EditText message = findViewById(R.id.message);

        Button send = findViewById(R.id.send_message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messages.add(new Message(message.getText().toString(), username, imageResource));
                messageAdapter.notifyDataSetChanged();
                message.getText().clear();
                //todo save it
            }
        });

    }


}
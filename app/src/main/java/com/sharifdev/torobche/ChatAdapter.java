package com.sharifdev.torobche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    ArrayList<String> names;
    Context context;
    ArrayList<Integer> imageIds;
    private static LayoutInflater inflater=null;

    public ChatAdapter( Context context, ArrayList<Integer> imageIds, ArrayList<String> names) {
        this.names = names;
        this.context=context;
        this.imageIds = imageIds;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView username;
        ImageView image;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.chat_item, null);

        holder.username=(TextView) view.findViewById(R.id.chat_text);
        holder.image=(ImageView) view.findViewById(R.id.chat_image);

        holder.username.setText(names.get(position));
        holder.image.setImageResource(imageIds.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
        return view;
    }

}
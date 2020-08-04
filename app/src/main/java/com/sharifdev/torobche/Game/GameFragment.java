package com.sharifdev.torobche.Game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.sharifdev.torobche.R;

public class GameFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        Button one_player_button = (Button) rootView.findViewById(R.id.one_player_btn);
        one_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseTopicFragment fragment= new ChooseTopicFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment, "choose_topic_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button multi_player_button = (Button) rootView.findViewById(R.id.multi_player_btn);
        multi_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseTopicFragment fragment= new ChooseTopicFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment, "choose_topic_fragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button group_button = (Button) rootView.findViewById(R.id.gp_btn);
        group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new  Intent(getActivity().getApplicationContext(), GameQuestionActivity.class);
                startActivity(i);
            }
        });


        return rootView;
    }
}

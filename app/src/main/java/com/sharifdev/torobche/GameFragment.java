package com.sharifdev.torobche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        Button one_player_button = (Button) rootView.findViewById(R.id.one_player_btn);

        Button multi_player_button = (Button) rootView.findViewById(R.id.multi_player_btn);

        Button group_button = (Button) rootView.findViewById(R.id.gp_btn);


        return rootView;
    }
}

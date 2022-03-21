package com.example.carbonjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SmartGamesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_smartgames, container, false);
        androidx.cardview.widget.CardView mathGamesEasy = rootView.findViewById(R.id.mathGamesEasy);
        mathGamesEasy.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),MathGames.class);
            intent.putExtra("level","easy");
            startActivity(intent);
        });
        androidx.cardview.widget.CardView mathGamesHard = rootView.findViewById(R.id.mathGamesHard);
        mathGamesHard.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),MathGames.class);
            intent.putExtra("level","hard");
            startActivity(intent);
        });
        return rootView;
    }
}

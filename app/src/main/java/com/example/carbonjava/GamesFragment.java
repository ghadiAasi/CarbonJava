package com.example.carbonjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GamesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_games, container, false);
        androidx.cardview.widget.CardView sudoku = rootView.findViewById(R.id.sudokuEasy);
        sudoku.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),SudokuActivity.class);
            intent.putExtra("level","easy");
            startActivity(intent);
        });
        sudoku =rootView.findViewById(R.id.sudokuHard);
        sudoku.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),SudokuActivity.class);
             intent.putExtra("level","hard");
            startActivity(intent);
        });

        androidx.cardview.widget.CardView tickTack = rootView.findViewById(R.id.tickTack);
        tickTack.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TicTacToeActivity.class);
            startActivity(intent);
        });
        return rootView;
    }
}

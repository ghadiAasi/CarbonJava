package com.example.carbonjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SmartGamesFragment extends Fragment {
    private androidx.cardview.widget.CardView sudoku;
    private androidx.cardview.widget.CardView tickTack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_smartgames, container, false);
        sudoku=rootView.findViewById(R.id.sudoku);
        sudoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SudokuActivity.class);
                startActivity(intent);
            }
        });
        tickTack=rootView.findViewById(R.id.tickTack);
        tickTack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TickTack.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
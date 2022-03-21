package com.example.carbonjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {
    private Button camera;
    private Button screenshot;
    private TextView userText;
    private TextView userEmail;
    private TextView levelSudokuE;
    private TextView levelSudokuH;
    private TextView levelMathE;
    private TextView levelMathH;
    private TextView bestTicTaToe;
     FirebaseDatabase database;
     DatabaseReference databaseReference;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "FIREBASE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_profile, container, false);

        userText = rootView.findViewById(R.id.usersName);
        userEmail = rootView.findViewById(R.id.usersText);

        userText.setText(mAuth.getCurrentUser().getProviderId());
        userEmail.setText(mAuth.getCurrentUser().getEmail());

        camera=rootView.findViewById(R.id.buttonCamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ProfileCamera.class);
                startActivity(intent);
            }
        });
        screenshot=rootView.findViewById(R.id.Array);
        screenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ArrayListActivity1.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

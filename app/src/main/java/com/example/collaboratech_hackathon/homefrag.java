package com.example.collaboratech_hackathon;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class homefrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_homefrag, container, false);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<taskList> foodItems = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String title = document.getString("title");
                                String description = document.getString("description");
                                String location = document.getString("description");
                                String price  = document.getString("price");

                                //String imageUrl = document.getString("image_url");

                                foodItems.add(new taskList(title, price, description, location, R.drawable.ic_launcher_foreground));
                            }
                            RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(new taskAdaptor(getContext(), foodItems));
                        } else {
                            Log.d("PAUL", "Error getting documents: ", task.getException());
                        }
                    }
                });


        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), addJob.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
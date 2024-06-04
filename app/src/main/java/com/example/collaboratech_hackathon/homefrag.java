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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class homefrag extends Fragment {

    private taskAdaptor homeListingCarAdapter;
    private List<taskList> HistoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefrag, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.taskAddRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        HistoryList = new ArrayList<>();
        homeListingCarAdapter = new taskAdaptor(getContext(), HistoryList, new taskAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(taskList trainer) {
                String historyUid = trainer.getUid(); // Assuming HomeListingClass has a getUid() method
                String histoyTitle = trainer.getTitle();
                String historyLocation = trainer.getAddress();
                String historyPrice = trainer.getPrice();




                Intent intent2 = new Intent(getContext(), JobAcceptTask.class);
                // Pass data as extras in the intent
                intent2.putExtra("historyUid", historyUid);
                intent2.putExtra("historyTitle", histoyTitle);
                intent2.putExtra("historyLocation", historyLocation);
                intent2.putExtra("historyPrice", historyPrice);
                startActivity(intent2);
            }
        });
        recyclerView.setAdapter(homeListingCarAdapter);



       fetchDataFromFirestore();
        // Test with local strings
        //populateWithTestData();

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



    private void fetchDataFromFirestore() {
        Log.d("TAEMALAKI", "Starting fetchDataFromFirestore");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("TAEMALAKI", "FirebaseFirestore instance obtained");

        db.collection("tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAEMALAKI", "Successfully connected to Firestore and fetched documents");
                            HistoryList.clear();
                            Log.d("TAEMALAKI", "HistoryList cleared, size: " + HistoryList.size());

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document != null) {
                                    Log.d("TAEMALAKI", "Document ID: " + document.getId());
                                    try {
                                        String title = document.getString("title");
                                        String description = document.getString("description");
                                        String location = document.getString("location");
                                        String priceLong = document.getString("price");
                                        String uid = document.getString("uid");

                                        if (title == null || description == null || location == null || priceLong == null) {
                                            Log.d("TAEMALAKI", "One or more fields are null in document ID: " + document.getId());
                                        } else {

                                            Log.d("TAEMALAKI", "Document data: title=" + title + ", description=" + description + ", location=" + location + ", price=" + priceLong);

                                            HistoryList.add(new taskList(title, priceLong, description, R.drawable.ic_launcher_foreground, uid));
                                            Log.d("TAEMALAKI", "Task added to HistoryList, current size: " + HistoryList.size());
                                        }
                                    } catch (Exception e) {
                                        Log.d("TAEMALAKI", "Error reading document fields in document ID: " + document.getId(), e);
                                    }
                                } else {
                                    Log.d("TAEMALAKI", "Null document in task result");
                                }
                            }

                            homeListingCarAdapter.notifyDataSetChanged();
                            Log.d("TAEMALAKI", "Adapter notified about data changes, total items: " + homeListingCarAdapter.getItemCount());
                        } else {
                            Log.d("TAEMALAKI", "Error getting documents: ", task.getException());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.d("TAEMALAKI", "Failed to connect to Firestore", e);
                });
    }

//    @Override
//    public void onItemClick(taskList history) {
//        String historyUid = history.getUid(); // Assuming HomeListingClass has a getUid() method
//        String histoyTitle = history.getTitle();
//
//
//
//        Intent intent2 = new Intent(getContext(), JobAcceptTask.class);
//        // Pass data as extras in the intent
//        intent2.putExtra("historyUid", historyUid);
//        intent2.putExtra("CarpostUID", carpostUID);
//        intent2.putExtra("ApprovedId", ApprovedId);
//        intent2.putExtra("CarOwnerId", carOwnerId);
//        startActivity(intent2);
//    }

}
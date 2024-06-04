package com.example.collaboratech_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class addJob extends AppCompatActivity {

    private FirebaseFirestore db;



    public void addTask(String title, String description, String location, String price) {

        Map<String, Object> task = new HashMap<>();
        task.put("title", title);
        task.put("description", description);
        task.put("location", location);
        task.put("price", price);
        db = FirebaseFirestore.getInstance();

        db.collection("tasks")
                .add(task)
                .addOnSuccessListener(documentReference -> {
                    Log.d("PAULAA", "addTask: " + documentReference.getId());
                    System.out.println("DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.d("PAULAA", "Error adding document: " + e);
                    System.err.println("Error adding document: " + e);
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_job);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button button = findViewById(R.id.postTask);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               TextView title = findViewById(R.id.taskTitle);
               TextView location = findViewById(R.id.address);
               TextView price = findViewById(R.id.taskPrice);
               TextView description = findViewById(R.id.taskDescription);

               String Title = title.getText().toString();
               String Description = description.getText().toString();
               String Location = location.getText().toString();
               String Price = price.getText().toString();

               Log.d("PAULAA", "GRABE");
               addTask(Title,Description, Location,Price);
           }
       });
        }
}


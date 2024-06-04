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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("tasks")
                .add(task)
                .addOnSuccessListener(documentReference -> {
                    String uid = documentReference.getId();
                    Log.d("PAULAA", "DocumentSnapshot added with ID: " + uid);

                    // Update the document with its UID
                    documentReference.update("uid", uid)
                            .addOnSuccessListener(aVoid -> Log.d("PAULAA", "Document UID updated successfully"))
                            .addOnFailureListener(e -> Log.d("PAULAA", "Error updating document UID: ", e));
                })
                .addOnFailureListener(e -> {
                    Log.d("PAULAA", "Error adding document: ", e);
                });
        Intent intent = new Intent(addJob.this, MainActivity.class);
        startActivity(intent);
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


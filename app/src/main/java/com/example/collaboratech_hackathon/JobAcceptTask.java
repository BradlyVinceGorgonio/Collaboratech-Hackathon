package com.example.collaboratech_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JobAcceptTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_accept_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();


        String title = intent.getStringExtra("historyTitle");
        String location = intent.getStringExtra("historyLocation");
        String price = intent.getStringExtra("historyPrice");
        String historyUid = intent.getStringExtra("historyUid");


        TextView TITLE = findViewById(R.id.taskTitle);
        TextView LOCATION = findViewById(R.id.address);
        TextView PRICE = findViewById(R.id.taskPrice);



    }
}
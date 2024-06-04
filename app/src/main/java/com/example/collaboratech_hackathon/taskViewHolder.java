package com.example.collaboratech_hackathon;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class taskViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView price;
    TextView address;
    ImageView imageView;
    public taskViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.jobtitle);
        price = itemView.findViewById(R.id.jobPrice);
        imageView = itemView.findViewById(R.id.jobimage);
        address = itemView.findViewById(R.id.jobAddress);
    }
}

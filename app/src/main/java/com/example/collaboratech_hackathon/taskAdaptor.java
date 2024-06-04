package com.example.collaboratech_hackathon;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class taskAdaptor extends RecyclerView.Adapter<taskViewHolder> {

Context context;
List<taskList> taskList;

    public taskAdaptor(Context context, List<com.example.collaboratech_hackathon.taskList> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new taskViewHolder(LayoutInflater.from(context).inflate(R.layout.joblist, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull taskViewHolder holder, int position) {
        holder.title.setText(taskList.get(position).title);
        holder.address.setText(taskList.get(position).address);
        holder.price.setText(taskList.get(position).price);
        holder.imageView.setImageResource(taskList.get(position).image);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}

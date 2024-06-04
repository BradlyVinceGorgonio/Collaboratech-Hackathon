package com.example.collaboratech_hackathon;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class taskAdaptor extends RecyclerView.Adapter<taskViewHolder> {
    private taskAdaptor.OnItemClickListener onItemClickListener; // Define the listener interface
Context context;
List<taskList> taskList;

    public interface OnItemClickListener {
        void onItemClick(taskList trainer);
    }


    public taskAdaptor(Context context, List<com.example.collaboratech_hackathon.taskList> taskList, taskAdaptor.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.taskList = taskList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new taskViewHolder(LayoutInflater.from(context).inflate(R.layout.joblist, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull taskViewHolder holder, int position) {
        taskList admin = taskList.get(position);
        holder.title.setText(taskList.get(position).title);
        holder.address.setText(taskList.get(position).address);
        holder.price.setText(taskList.get(position).price);
        holder.imageView.setImageResource(taskList.get(position).image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(admin);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }
}

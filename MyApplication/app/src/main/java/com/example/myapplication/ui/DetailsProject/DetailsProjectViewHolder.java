package com.example.myapplication.ui.DetailsProject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DetailsProjectViewHolder extends RecyclerView.ViewHolder{
    TextView startTime;
    TextView endTime;
    TextView totalHours;
    public DetailsProjectViewHolder(@NonNull View itemView) {
        super(itemView);
        startTime=itemView.findViewById(R.id.startTime);
        endTime=itemView.findViewById(R.id.endTime);
        totalHours=itemView.findViewById(R.id.totalHours);
    }
}
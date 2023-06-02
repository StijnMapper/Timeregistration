package com.example.myapplication.ui.DetailsProject;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DetailsProjectViewHolder extends RecyclerView.ViewHolder{
    TextView startTime;
    TextView endTime;
    TextView totalHours;
    ImageButton delete;
    ImageButton edit;
    public DetailsProjectViewHolder(@NonNull View itemView) {
        super(itemView);
        startTime=itemView.findViewById(R.id.startDate);
        endTime=itemView.findViewById(R.id.startTime);
       totalHours=itemView.findViewById(R.id.totalHours);
        delete = itemView.findViewById(R.id.delete);
        edit = itemView.findViewById(R.id.edit);
    }
}

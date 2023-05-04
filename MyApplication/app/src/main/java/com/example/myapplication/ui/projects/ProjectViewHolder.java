package com.example.myapplication.ui.projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton add;


        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.projectName);
            add=itemView.findViewById(R.id.button_add);
        }


    }



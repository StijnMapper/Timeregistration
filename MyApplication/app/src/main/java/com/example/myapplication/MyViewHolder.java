package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    Button button;
    Button edit;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.item);
        button=itemView.findViewById(R.id.delete);
        edit=itemView.findViewById(R.id.edit);
    }
}

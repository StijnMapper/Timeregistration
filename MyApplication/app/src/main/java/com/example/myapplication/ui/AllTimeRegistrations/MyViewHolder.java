package com.example.myapplication.ui.AllTimeRegistrations;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageButton button;
    ImageButton edit;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.item);
        button=itemView.findViewById(R.id.delete);
        edit=itemView.findViewById(R.id.edit);
        //add=itemView.findViewById(R.id.button_add);
    }

}

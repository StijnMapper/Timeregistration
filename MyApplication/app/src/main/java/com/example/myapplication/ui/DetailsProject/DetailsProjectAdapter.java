package com.example.myapplication.ui.DetailsProject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.TimeRegistration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailsProjectAdapter extends RecyclerView.Adapter<DetailsProjectAdapter.RegistrationViewHolder> {

    private Context context;
    private List<TimeRegistration> timeRegistrations= new ArrayList<>();
    public void setRegistrations(List<TimeRegistration> registrations) {
        this.timeRegistrations=registrations;
        notifyDataSetChanged();
    }

    public DetailsProjectAdapter(Context context, List<TimeRegistration> timeRegistrations) {
        this.context = context;
        this.timeRegistrations = timeRegistrations;
    }

    @NonNull
    @Override
    public RegistrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_list_item_tijdsregistratie, parent, false);
        return new RegistrationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RegistrationViewHolder holder, int position) {
        TimeRegistration timeRegistration = timeRegistrations.get(position);
            holder.startTime.setText(getFormattedTime(timeRegistration.getTimer().getStartTime()));
            holder.endTime.setText(getFormattedTime(timeRegistration.getTimer().getEndTime()));
    }


    @Override
    public int getItemCount() {
        Log.d("RegistrationAdapter", "registrations: " +  timeRegistrations.size());
        if (timeRegistrations == null) {
            return 0;
        }
        return timeRegistrations.size();

    }

    public void setTimeRegistrations(List<TimeRegistration> timeRegistrations) {
        this.timeRegistrations = timeRegistrations;
        notifyDataSetChanged();
    }

    public static class RegistrationViewHolder extends RecyclerView.ViewHolder {
        private TextView startTime;
        private TextView endTime;
        private TextView totalHours;

        public RegistrationViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            totalHours = itemView.findViewById(R.id.totalHours);

        }
    }

    // method to format the time as a string of timeregistrations
    private String getFormattedTime(String timeString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
        String formattedTime = "";
        try {
            Date date = inputFormat.parse(timeString);
            formattedTime = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTime;
    }}

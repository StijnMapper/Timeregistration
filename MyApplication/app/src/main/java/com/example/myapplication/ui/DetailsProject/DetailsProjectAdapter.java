package com.example.myapplication.ui.DetailsProject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProjectAdapter extends RecyclerView.Adapter<DetailsProjectAdapter.RegistrationViewHolder> {

    private Context context;
    private List<TimeRegistration> timeRegistrations= new ArrayList<>();

    private DetailsProject fragment;
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
        TimeRegistration timeRegistration = timeRegistrations.get(holder.getBindingAdapterPosition());
            holder.startTime.setText(getFormattedTime(timeRegistration.getTimer().getStartTime()));
            holder.endTime.setText(getFormattedTime(timeRegistration.getTimer().getEndTime()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timeRegistrationId = timeRegistrations.get(holder.getBindingAdapterPosition()).getRegistrationId();
                int timerId = timeRegistrations.get(holder.getBindingAdapterPosition()).getTimer().getTimerId();
                Log.d(DetailsProject.class.getSimpleName(), "registrationId " + timeRegistrationId + ", timerId " + timerId);
                deleteTimeRegistration(timeRegistrationId);

            }});

    }
    public void deleteTimeRegistration(int timeRegistrationId) {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        Call<Void> callDelete = service.deleteTimeRegistration(timeRegistrationId);
        callDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    // Remove the deleted time registration from the list
                    int indexToRemove = -1;
                    for (int i = 0; i < timeRegistrations.size(); i++) {
                        TimeRegistration tr = timeRegistrations.get(i);
                        if (tr.getRegistrationId() == timeRegistrationId) {
                            indexToRemove = i;
                            Log.d(DetailsProject.class.getSimpleName(), "registrationId " + timeRegistrationId);
                            break;
                        }
                    }
                    Log.d(DetailsProject.class.getSimpleName(), "registrationId " + timeRegistrationId);

                    if (indexToRemove >= 0) {
                        timeRegistrations.remove(indexToRemove);
                        notifyDataSetChanged();
                        //(indexToRemove);
                    }

                    Log.d(DetailsProject.class.getSimpleName(), "Time registration deleted successfully");
                } else {
                    Log.e(DetailsProject.class.getSimpleName(), "Error deleting time registration: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(DetailsProject.class.getSimpleName(), t.getMessage(), t);
            }
        });


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
        private ImageButton delete;
        private ImageButton edit;

        public RegistrationViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            totalHours = itemView.findViewById(R.id.totalHours);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);



        }

    }
    public TimeRegistration getItem(int position) {
        return timeRegistrations.get(position);
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
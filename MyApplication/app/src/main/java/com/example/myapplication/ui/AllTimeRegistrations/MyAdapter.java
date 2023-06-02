package com.example.myapplication.ui.AllTimeRegistrations;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailsTimeRegist;
import com.example.myapplication.R;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;
import com.example.myapplication.ui.DetailsProject.DetailsProjectAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kotlin.contracts.Returns;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<TimeRegistration> timeRegistrations = new ArrayList<>();

    private Context context;

    public MyAdapter(Context context, List<TimeRegistration> timeRegistrations) {
        this.context = context;
        this.timeRegistrations = timeRegistrations;
    }

    public void setTimeRegistrations(List<TimeRegistration> timeRegistrations) {
        Log.d(MyAdapter.class.getSimpleName(), "setRegistrations: " + timeRegistrations.size());
        this.timeRegistrations = timeRegistrations;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //  holder.textView.setText(data[position]);
        TimeRegistration timeRegistration = timeRegistrations.get(holder.getBindingAdapterPosition());

        //  holder.nameTextView.setText(project.getName());
        holder.registrationText.setText(getFormattedTime(timeRegistration.getTimer().getStartTime()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_time_to_detailsTimeRegist);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_navigation_time_to_detailsTimeRegist);
                Bundle bundle = new Bundle();

                // Haal de huidige tijdregistratie op uit de lijst
                TimeRegistration timeRegistration = timeRegistrations.get(position);
                timeRegistration.getProject().setProjectId(timeRegistration.getProject().getProjectId());
                timeRegistration.setRegistrationId(timeRegistration.getRegistrationId());

                // Zet de gegevens in de bundel om door te geven aan withoutimerfragment
                bundle.putInt("registrationId", timeRegistration.getRegistrationId());
                bundle.putInt("projectId", timeRegistration.getProject().getProjectId());
                bundle.putString("startDate", formatDate(timeRegistration.getTimer().getStartTime()));
                bundle.putString("endDate", formatDate(timeRegistration.getTimer().getEndTime()));
                bundle.putString("startTime", formatTime(timeRegistration.getTimer().getStartTime()));
                bundle.putString("endTime", formatTime(timeRegistration.getTimer().getEndTime()));
                bundle.putString("tags", timeRegistration.getTask().getTags());
                bundle.putString("task", timeRegistration.getTask().getName());
                DetailsTimeRegist fragment = new DetailsTimeRegist();
                fragment.setArguments(bundle);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Haal de positie van de tijdregistratie op
                int position = holder.getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Haal de tijdregistratie op basis van de positie
                    TimeRegistration timeRegistration = timeRegistrations.get(position);

                    // Haal de ID's op voor het verwijderen
                    int timeRegistrationId = timeRegistration.getRegistrationId();
                    int timerId = timeRegistration.getTimer().getTimerId();

                    Log.d(DetailsProjectAdapter.class.getSimpleName(), "registrationId " + timeRegistrationId + ", timerId " + timerId);
                    Log.d(DetailsProjectAdapter.class.getSimpleName(), String.valueOf(timeRegistration.getProject().getProjectId()));

                    // Verwijder de tijdregistratie
                    deleteTimeRegistration(timeRegistrationId);
                    // update de tijdregistratie
                    updateTimeRegistration(timeRegistrationId,timeRegistration);

                }
            }

        });

    }

    public void updateTimeRegistration(int timeRegistrationId, TimeRegistration timeRegistrationDetails) {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        Call<TimeRegistration> call = service.updateTimeRegistration(timeRegistrationId, timeRegistrationDetails);

        call.enqueue(new Callback<TimeRegistration>() {
            @Override
            public void onResponse(Call<TimeRegistration> call, Response<TimeRegistration> response) {
                if (response.isSuccessful()) {
                    TimeRegistration updatedTimeRegistration = response.body();
                    // Handle the updated time registration
                    Log.d(MyAdapter.class.getSimpleName(), "Time registration updated: " + updatedTimeRegistration);
                } else if (response.code() == 404) {
                    Log.d(MyAdapter.class.getSimpleName(), "Time registration not found");
                } else {
                    Log.e(MyAdapter.class.getSimpleName(), "Failed to update time registration: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TimeRegistration> call, Throwable t) {
                Log.e(MyAdapter.class.getSimpleName(), "Error updating time registration: " + t.getMessage(), t);
            }
        });
    }


    public void deleteTimeRegistration(int registrationId) {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        Call<Void> call = service.deleteTimeRegistration(registrationId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // verwijder de verwijderde registratie ook van de lijst
                    int indexToRemove = -1;
                    for (int i = 0; i < timeRegistrations.size(); i++) {
                        TimeRegistration tr = timeRegistrations.get(i);
                        if (tr.getRegistrationId() == registrationId) {
                            indexToRemove = i;
                            Log.d(MyAdapter.class.getSimpleName(), "registrationId " + registrationId);
                            break;
                        }
                    }
                    if (indexToRemove >= 0) {
                        timeRegistrations.remove(indexToRemove);
                        notifyDataSetChanged();
                    }
                    Log.d(MyAdapter.class.getSimpleName(), "Time registration deleted successfully");
                } else {
                    Log.e(MyAdapter.class.getSimpleName(), "Error deleting time registration: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(MyAdapter.class.getSimpleName(), t.getMessage(), t);
            }
        });
    }



    @Override
    public int getItemCount() {
        int itemCount = timeRegistrations == null ? 0 : timeRegistrations.size();
        Log.d(MyAdapter.class.getSimpleName(), "getItemCount: " + itemCount);
        return itemCount;
    }
    static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView registrationText;
        ImageButton edit;
        ImageButton delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            registrationText = itemView.findViewById(R.id.item);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
    private String formatDate(String dateString) {
        //Maak input en uitput date fromat isntanties
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = "";
        try {
            // Parse input date string
            Date date = inputFormat.parse(dateString);
            // Formateer de parsed date naar dd/MM/yyyy
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    private String formatTime(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String formattedDate = "";
        try {
            Date date = inputFormat.parse(dateString);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
    private String getFormattedTime(String timeString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        String formattedTime = "";
        try {
            Date date = inputFormat.parse(timeString);
            formattedTime = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTime;
    }
}

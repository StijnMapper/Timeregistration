package com.example.myapplication.ui.DetailsProject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProjectAdapter extends RecyclerView.Adapter<DetailsProjectAdapter.RegistrationViewHolder> {

    private Context context;
    private List<TimeRegistration> timeRegistrations = new ArrayList<>();

    private DetailsProject fragment;

    // Methode om de lijst met tijdregistraties bij te werken
    public void setRegistrations(List<TimeRegistration> registrations) {
        this.timeRegistrations = registrations;
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

    // Methode voor het binden van de gegevens aan de view holder
    @Override
    public void onBindViewHolder(@NonNull RegistrationViewHolder holder, int position) {
        // Haal de tijdregistratie op voor de huidige positie
        TimeRegistration timeRegistration = timeRegistrations.get(holder.getBindingAdapterPosition());
        // Zet de starttijd en eindtijd in de juiste tekstvelden
        holder.startTime.setText(getFormattedTime(timeRegistration.getTimer().getStartTime()));
        holder.endTime.setText(getFormattedTime(timeRegistration.getTimer().getEndTime()));
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
                    deleteTimeRegistration(timeRegistrationId, timeRegistration.getProject().getProjectId());
                    // update de tijdregistratie
                    updateTimeRegistration(timeRegistration.getProject().getProjectId(), timeRegistrationId, timeRegistration);

                }
            }

        });

        //navigate to details of time registration
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Maak een nieuwe bundel aan om gegevens door te geven aan het volgende fragment(withoutimer)
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

                // Navigeer naar het volgende fragment (withouttimer) met de bundel als argument
                Navigation.findNavController(view).navigate(R.id.action_detailsProject_to_withoutTimerFragment, bundle);
            }
        });
    }

    public void updateTimeRegistration(int projectId, int registrationId, TimeRegistration timeRegistration) {
        // Maak een instantie van de TimeRegistrationService met behulp van RetrofitClient
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        // Maak een API-aanroep om de tijdregistratie te updaten
        Call<TimeRegistration> call = service.updateTimeRegistration(projectId, registrationId, timeRegistration);
        call.enqueue(new Callback<TimeRegistration>() {
            @Override
            public void onResponse(Call<TimeRegistration> call, Response<TimeRegistration> response) {
                if (response.isSuccessful()) {
                    // Update de bijgewerkte tijdregistratie in de lijst
                    for (int i = 0; i < timeRegistrations.size(); i++) {
                        TimeRegistration tr = timeRegistrations.get(i);
                        if (tr.getRegistrationId() == registrationId) {
                            timeRegistrations.set(i, response.body());
                            notifyDataSetChanged();
                            break;
                        }
                    }

                    Log.d(DetailsProjectAdapter.class.getSimpleName(), "Tijdregistratie succesvol bijgewerkt");
                } else {
                    Log.e(DetailsProjectAdapter.class.getSimpleName(), "Fout bij bijwerken van tijdregistratie: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TimeRegistration> call, Throwable t) {
                Log.e(DetailsProjectAdapter.class.getSimpleName(), t.getMessage(), t);
            }
        });
    }

    public void deleteTimeRegistration(int timeRegistrationId, int projectId) {
        // Maak een instantie van de TimeRegistrationService met behulp van RetrofitClient
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);

        // Maak een API-aanroep om de tijdregistratie te verwijderen
        Call<Void> callDelete = service.deleteTimeRegistrationsByProjectId(projectId, timeRegistrationId);
        callDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // verwijder de verwijderde registratie ook van de lijst
                    int indexToRemove = -1;
                    for (int i = 0; i < timeRegistrations.size(); i++) {
                        TimeRegistration tr = timeRegistrations.get(i);
                        if (tr.getRegistrationId() == timeRegistrationId) {
                            indexToRemove = i;
                            Log.d(DetailsProjectAdapter.class.getSimpleName(), "registrationId " + timeRegistrationId);
                            break;
                        }
                    }
                    if (indexToRemove >= 0) {
                        timeRegistrations.remove(indexToRemove);
                        notifyDataSetChanged();
                    }
                    Log.d(DetailsProjectAdapter.class.getSimpleName(), "Time registration deleted successfully");
                } else {
                    Log.e(DetailsProjectAdapter.class.getSimpleName(), "Error deleting time registration: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(DetailsProjectAdapter.class.getSimpleName(), t.getMessage(), t);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Log het aantal tijdregistraties in de lijst
        Log.d(DetailsProjectAdapter.class.getSimpleName(), "registrations: " + timeRegistrations.size());
        if (timeRegistrations == null) {
            return 0; // Controleer of de lijst null is
        }
        // Retourneer het aantal tijdregistraties in de lijst
        return timeRegistrations.size();
    }


    public static class RegistrationViewHolder extends RecyclerView.ViewHolder {
        private TextView startTime;
        private TextView endTime;
        private TextView totalHours;
        private ImageButton delete;
        private ImageButton edit;
        private Button save;

        //1 item van de recylerview van project
        //referenties naar de weergave-elementen van een enkel item in de lijst
        public RegistrationViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            totalHours = itemView.findViewById(R.id.totalHours);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
            save = itemView.findViewById(R.id.save);
        }
    }

    /**
     * Format date string van "yyyy-MM-dd'T'HH:mm:ss" format naar "dd/MM/yyyy" format.
     *
     * @param dateString De input date string in "yyyy-MM-dd'T'HH:mm:ss" format.
     * @return De geformateerde date string in "dd/MM/yyyy" format.
     */
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
    }
}
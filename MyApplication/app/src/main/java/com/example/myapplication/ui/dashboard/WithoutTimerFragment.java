package com.example.myapplication.ui.dashboard;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Task;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.data.model.Timer;
import com.example.myapplication.databinding.FragmentWithoutTimerBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;
import com.example.myapplication.ui.DetailsProject.DetailsProjectAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithoutTimerFragment extends Fragment {
    private int projectId;
    private int registrationId;
    private DetailsProjectAdapter adapter;
    private List<TimeRegistration> timeRegistrations = new ArrayList<>();
    private Button btnStartDate;
    private Button btnStartTime;
    private Button btnEndDate;
    private Button btnEndTime;

    private EditText TextTags;
    private EditText Texttask;
    private Calendar calendar;
    private TimeRegistration timeRegistration;

    public WithoutTimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ontvang de argumenten van de DetailsProject
        if (getArguments() != null) {
            // Haal de waarden op uit de argumenten
            registrationId = getArguments().getInt("registrationId");
            projectId = getArguments().getInt("projectId");
            timeRegistration = getArguments().getParcelable("timeRegistration");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Update time registration");
        }
        FragmentWithoutTimerBinding binding = FragmentWithoutTimerBinding.inflate(inflater, container, false);

        // Koppel de weergave-elementen aan de variabelen
        btnEndDate = binding.endDate;
        btnEndTime = binding.endTime;
        btnStartDate = binding.startDate;
        btnStartTime = binding.startTime;
        TextTags = binding.tags;
        Texttask = binding.task;

        Bundle bundle = getArguments();

        if (bundle != null) {
            // Ontvang de waarden van de argumenten van DetailsprojectAdapter
            String startDate = bundle.getString("startDate");
            String startTime = bundle.getString("startTime");
            String endDate = bundle.getString("endDate");
            String endTime = bundle.getString("endTime");
            String tags = bundle.getString("tags");
            String task = bundle.getString("task");

            // Stel de waarden in op de weergave-elementen
            btnStartTime.setText(startTime);
            btnStartDate.setText(startDate);
            btnEndDate.setText(endDate);
            btnEndTime.setText(endTime);
            TextTags.setText(tags);
            Texttask.setText(task);
        }

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        //Toon Datepicker en TimePicker
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        return binding.getRoot();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                btnStartDate.setText(dateFormat.format(calendar.getTime()));
                btnEndDate.setText(dateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                btnStartTime.setText(timeFormat.format(calendar.getTime()));
                btnEndTime.setText(timeFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    calendar = Calendar.getInstance();

    // Andere code ...

    Button saveButton = view.findViewById(R.id.save);
    saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Haal de geselecteerde datum en tijd op
            String selectedStartDate = btnStartDate.getText().toString();
            String selectedEndDate = btnEndDate.getText().toString();
            String selectedStartTime = btnStartTime.getText().toString();
            String selectedEndTime = btnEndTime.getText().toString();
            String selectedTask = Texttask.getText().toString();
            String selectedTags = TextTags.getText().toString();
            timeRegistration = getArguments().getParcelable("timeRegistration");

            // Converteer de geselecteerde datum en tijd naar het backend-formaat
            SimpleDateFormat frontendDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat backendDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {
                Date selectedStartDateTime = frontendDateFormat.parse(selectedStartDate + " " + selectedStartTime);
                Date selectedEndDateTime = frontendDateFormat.parse(selectedEndDate + " " + selectedEndTime);
                String formattedStartDateTime = backendDateFormat.format(selectedStartDateTime);
                String formattedEndDateTime = backendDateFormat.format(selectedEndDateTime);

                if (timeRegistration == null) {
                    timeRegistration = new TimeRegistration();

                }
                // Check if Timer is null and create a new Timer object
                if (timeRegistration.getTimer() == null) {
                    Timer timer = new Timer();
                    timeRegistration.setTimer(timer);
                }
                // Check if Task is null and create a new Task object
                if (timeRegistration.getTask() == null) {
                    Task task = new Task();
                    timeRegistration.setTask(task);
                }

          //      timeRegistration.getTimer().setStartTime(formattedDateTime);
                timeRegistration.getTask().setName(selectedTask);
                timeRegistration.getTask().setTags(selectedTags);
                timeRegistration.getTimer().setStartTime(formattedStartDateTime);
                timeRegistration.getTimer().setEndTime(formattedEndDateTime);
                updateTimeRegistration(projectId, registrationId, timeRegistration);
                Log.d(WithoutTimerFragment.class.getSimpleName(), projectId + " registerid" + registrationId + " " + timeRegistration);

                // Terug naar het vorige scherm ...
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    });
}



    public void updateTimeRegistration(int projectId, int registrationId, TimeRegistration timeRegistration) {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
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
                            break;
                        }
                    }
                    if (timeRegistration.getTask() != null) {
                        String taskName = timeRegistration.getTask().getName();
                        // Voer verdere logica uit met de taaknaam
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

}



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

        Bundle bundle = getArguments();

        if (bundle != null) {
            // Ontvang de waarden van de argumenten van DetailsprojectAdapter
            String startDate = bundle.getString("startDate");
            String startTime = bundle.getString("startTime");
            String endDate = bundle.getString("endDate");
            String endTime = bundle.getString("endTime");

            // Stel de waarden in op de weergave-elementen
            btnStartTime.setText(startTime);
            btnStartDate.setText(startDate);
            btnEndDate.setText(endDate);
            btnEndTime.setText(endTime);
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
        // Haal einddatum en startdatum op uit de argumenten
        String startTime = getArguments().getString("startTime");
        String endDate = getArguments().getString("endDate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse naar Date-objecten
            Date startDate = dateFormat.parse(startTime);
            Date endDatee = dateFormat.parse(endDate);

            calendar.setTime(startDate);
            calendar.setTime(endDatee);

            // Stel de startdatum en einddatum in op de bijbehorende views
            btnStartDate.setText(dateFormat.format(startDate));
            btnEndDate.setText(dateFormat.format(endDatee));

        } catch (ParseException e) {
            e.printStackTrace();
        }


    adapter = new DetailsProjectAdapter(requireContext(), timeRegistrations);
        Button saveButton = view.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Haal de geselecteerde datum en tijd op
                String selectedDate = btnStartDate.getText().toString();
                String selectedTime = btnStartTime.getText().toString();
                timeRegistration = getArguments().getParcelable("timeRegistration");

                //set timeregsitration
                TimeRegistration timeRegistration = new TimeRegistration();
                timeRegistration.setRegistrationId(2);

                Timer timer = new Timer();
                timer.setTimerId(1);
                timer.setDuration(25);
                // Wijs de Timer toe aan de tijdregistratie
                timeRegistration.setTimer(timer);

                Task task = new Task();
                task.setName("task7");
                task.setTags("tag7");
                timeRegistration.setTask(task);

                //update time registration
                updateTimeRegistration(projectId, registrationId, timeRegistration);
                Log.d(WithoutTimerFragment.class.getSimpleName(), "projectid: " + projectId + " registeid: " + registrationId);
                // Ga terug naar het vorige scherm
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
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



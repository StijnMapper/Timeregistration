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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;
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
    private Button btnDate;
    private Button btnTime;
    private Calendar calendar;
    private TimeRegistration timeRegistration;

    public WithoutTimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
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
        View rootView = inflater.inflate(R.layout.fragment_without_timer, container, false);
        FragmentWithoutTimerBinding binding = FragmentWithoutTimerBinding.inflate(inflater, container, false);
        Button startDateText = binding.startTime;
        Button startTimeText = binding.endTime;

        Bundle bundle = getArguments();

        if (bundle != null) {
            String startDate = bundle.getString("startDate");
            String endTime = bundle.getString("endTime");

            startTimeText.setText(endTime);

            // Bijwerken van de startdatumknop
            startDateText.setText(startDate);
        }


        btnDate = binding.startTime;
        btnTime = binding.endTime;

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
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
                btnDate.setText(dateFormat.format(calendar.getTime()));
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
                btnTime.setText(timeFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendar = Calendar.getInstance();

        String startTime = getArguments().getString("startDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = dateFormat.parse(startTime);
            calendar.setTime(startDate);
            btnDate.setText(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
       //
        adapter = new DetailsProjectAdapter(requireContext(), timeRegistrations);
        Button saveButton = view.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Haal de geselecteerde datum en tijd op
                String selectedDate = btnDate.getText().toString();
                String selectedTime = btnTime.getText().toString();
                timeRegistration = getArguments().getParcelable("timeRegistration");

                // Voer hier de logica uit om de tijdregistratie bij te werken met de geselecteerde datum en tijd
                // ...

                // Toon een melding aan de gebruiker om aan te geven dat de tijdregistratie is bijgewerkt
                Toast.makeText(getContext(), "Tijdregistratie bijgewerkt", Toast.LENGTH_SHORT).show();
                //set timeregsitration
                TimeRegistration timeRegistration = new TimeRegistration();
                timeRegistration.setRegistrationId(2);

                Timer timer = new Timer();
                timer.setTimerId(1);
                timer.setStartTime(null);
                timer.setEndTime(null);
                timer.setDuration(25);
                timeRegistration.setTimer(timer);

                Task task = new Task();
                task.setName("task7");
                task.setTags("tag7");
                timeRegistration.setTask(task);

                Project project = new Project();
                project.setProjectId(1);
                project.setName("project1");
                project.setStatus(null);
                project.setDescription("descirption project3");
                project.setUser(null);
                timeRegistration.setProject(project);

                //update time registration
                updateTimeRegistration(projectId, registrationId,timeRegistration);
                Log.d(WithoutTimerFragment.class.getSimpleName(),"projectid: "+projectId + " registeid: "+registrationId);
                // Ga terug naar het vorige scherm
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();

            }
        });
    }
    public void updateTimeRegistration(int projectId, int registrationId,TimeRegistration timeRegistration) {
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



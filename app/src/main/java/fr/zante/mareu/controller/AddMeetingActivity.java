package fr.zante.mareu.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.di.Injection;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;
import fr.zante.mareu.repository.Repository;

public class AddMeetingActivity extends AppCompatActivity {

    private ImageView buttonPreviousPage;
    private DatePicker datePicker;
    private MeetingDate meetingDate;
    private TextInputLayout topicInput;
    private Spinner meetingRoomSpinner;
    private MeetingRoom meetingRoom;
    private Spinner timeSlotSpinner;
    private TimeSlot timeSlot;
    private ImageView addMemberToListButton;
    private MaterialButton addMeetingButton;

    private Repository repository;
    private List<Member> membersForNewMeeting;

    private static final int ADD_MEMBER_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADD_MEMBER_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            assert data != null;
            membersForNewMeeting = (List<Member>) data.getSerializableExtra(AddMemberActivity.BUNDLE_LIST_MEMBER_CREATED);
            Log.d("onActivityResult", "list size = " + membersForNewMeeting.size());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        buttonPreviousPage = findViewById(R.id.button_previous_page);
        datePicker = findViewById(R.id.add_meeting_date_picker);
        topicInput = findViewById(R.id.add_activity_textinputlayout_topic);
        meetingRoomSpinner = findViewById(R.id.add_activity_spinner_meeting_room);
        timeSlotSpinner = findViewById(R.id.add_activity_spinner_time_slot);
        addMemberToListButton = findViewById(R.id.add_activity_add_member_to_list_button);
        addMeetingButton = findViewById(R.id.add_meeting_add_button);

        repository = Injection.getRepository();
        membersForNewMeeting = new ArrayList<>();

        configurePreviousPageButton();
        configureDatePicker();
        configureMeetingRoomSpinner();
        configureTimeSlotSpinner();
        configureAddMemberToListButton();
        configureAddMeetingButton();
    }

    private void configurePreviousPageButton() {
        buttonPreviousPage.setOnClickListener(view -> {
            finish();
        });
    }

    private void configureDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                long newMeetingDateId = buildNewMeetingDateId();
                meetingDate = new MeetingDate(newMeetingDateId, year, month+1, dayOfMonth, repository.getMeetingRooms());
                Log.d("meetingDate created :", "configuredatapicker: onDateChanged: " + meetingDate.getDay() + "/" + meetingDate.getMonth() + "/" + meetingDate.getYear());
                addMeetingDateToMyList();
                configureMeetingRoomSpinner();
                configureTimeSlotSpinner();
            }
        });

        long meetingDateId = buildNewMeetingDateId();
        meetingDate = new MeetingDate(meetingDateId, datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth(), repository.getMeetingRooms());
        Log.d("meetingDate created :", "configuredatapicker: " + meetingDate.getDay() + "/" + meetingDate.getMonth() + "/" + meetingDate.getYear());
        addMeetingDateToMyList();
    }

    private void addMeetingDateToMyList() {
        boolean isAvailable = false;
        List<MeetingDate> availableDate = repository.getMeetingDates();
        for (int i=0; i<availableDate.size(); i++) {
            if(availableDate.get(i).getYear() == meetingDate.getYear() && availableDate.get(i).getMonth() == meetingDate.getMonth() && availableDate.get(i).getDay() == meetingDate.getDay()) {
                isAvailable = true;
                meetingDate = availableDate.get(i);
                break;
            }
        }
        if (!isAvailable) {
            repository.addMeetingDate(meetingDate);
        }
        Log.d("meetingDateListSize", "addMeetingDateToMyList: nombre d'elements dans la liste = " + repository.getMeetingDates().size() + " dernier index: " + repository.getMeetingDates().get(repository.getMeetingDates().size()-1).getId());
    }

    private void configureMeetingRoomSpinner() {
        List<MeetingRoom> meetingRoomsList = meetingDate.getMeetingRooms();
        MeetingRoomSpinnerAdapter meetingRoomSpinnerAdapter = new MeetingRoomSpinnerAdapter(this,R.layout.item_meeting_room_spinner, meetingRoomsList);
        meetingRoomSpinner.setAdapter(meetingRoomSpinnerAdapter);
        meetingRoom = meetingRoomsList.get(0);
        meetingRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                meetingRoom = (MeetingRoom) adapterView.getItemAtPosition(i);
                configureTimeSlotSpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void configureTimeSlotSpinner() {
        List<TimeSlot> timeSlotsList = meetingRoom.getTimeSlots();
        List<TimeSlot> timeSlotsIsFreeList = new ArrayList<>();
        for (int i=0; i<timeSlotsList.size(); i++) {
            if (timeSlotsList.get(i).isFree()) {
                timeSlotsIsFreeList.add(timeSlotsList.get(i));
            }
        }
        TimeSlotSpinnerAdapter timeSlotSpinnerAdapter = new TimeSlotSpinnerAdapter(this,R.layout.item_time_slop_spinner, timeSlotsIsFreeList);
        timeSlotSpinner.setAdapter(timeSlotSpinnerAdapter);
        timeSlotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeSlot = (TimeSlot) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void configureAddMemberToListButton() {
        addMemberToListButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddMemberActivity.class);
            Bundle myBundle = new Bundle();
            myBundle.putSerializable("MEMBERS_LIST", (Serializable) membersForNewMeeting);
            intent.putExtra("BUNDLE_MEMBERS_LIST", myBundle);
            startActivityForResult(intent, ADD_MEMBER_ACTIVITY_REQUEST_CODE);
        });
    }

    private void configureAddMeetingButton() {
        addMeetingButton.setOnClickListener(view -> {
            // CREATE A MEETING :
            long meetingId = buildNewMeetingId();

            Meeting meeting = new Meeting(
                    meetingId,
                    topicInput.getEditText().getText().toString(),
                    meetingRoom,
                    meetingDate,
                    timeSlot,
                    membersForNewMeeting
            );
            repository.addMeeting(meeting);

            // UPDATE MEETING DATE:
            timeSlot.setFree(false);
            repository.updateMeetingDate(meetingDate);

            finish();
        });
    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    private long buildNewMeetingId() {
        // Create new ID :
        long newId;
        if (repository.getMeetings().size() == 0) {
            newId = 0;
        } else {
            int lastPositionInMeetingsList = repository.getMeetings().size() - 1;
            newId = repository.getMeetings().get(lastPositionInMeetingsList).getId() + 1;
        }
        return newId;
    }

    private long buildNewMeetingDateId() {
        long newMeetingDateId;
        if (repository.getMeetingDates().size() == 0) {
            newMeetingDateId = 0;
        } else {
            int lastPositionInMeetingDatesList = repository.getMeetingDates().size()-1;
            newMeetingDateId = repository.getMeetingDates().get(lastPositionInMeetingDatesList).getId() + 1;
        }
        return newMeetingDateId;
    }
}

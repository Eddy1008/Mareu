package fr.zante.mareu.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.di.Injection;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;
import fr.zante.mareu.repository.Repository;

public class AddMeetingActivity extends AppCompatActivity {

    private ImageView buttonPreviousPage;
    private TextInputLayout topicInput;
    private Spinner meetingRoomSpinner;
    private MeetingRoom meetingRoom;
    private TextInputLayout dateInput;
    private Spinner timeSlotSpinner;
    private TimeSlot timeSlot;
    private MaterialButton addMeetingButton;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        buttonPreviousPage = findViewById(R.id.button_previous_page);
        topicInput = findViewById(R.id.add_activity_textinputlayout_topic);
        meetingRoomSpinner = findViewById(R.id.add_activity_spinner_meeting_room);
        dateInput = findViewById(R.id.add_activity_textinputlayout_date);
        timeSlotSpinner = findViewById(R.id.add_activity_spinner_time_slot);
        addMeetingButton = findViewById(R.id.add_meeting_add_button);

        repository = Injection.getRepository();

        configurePreviousPageButton();
        configureMeetingRoomSpinner();
        configureTimeSlotSpinner();
        configureAddMeetingButton();
    }

    private void configurePreviousPageButton() {
        buttonPreviousPage.setOnClickListener(view -> {
            finish();
        });
    }

    private void configureMeetingRoomSpinner() {
        // TODO change meetingRoomsList source with selected date
        List<MeetingRoom> meetingRoomsList = repository.getMeetingRooms();
        MeetingRoomSpinnerAdapter meetingRoomSpinnerAdapter = new MeetingRoomSpinnerAdapter(this,R.layout.item_meeting_room_spinner, meetingRoomsList);
        meetingRoomSpinner.setAdapter(meetingRoomSpinnerAdapter);
        meetingRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                meetingRoom = (MeetingRoom) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void configureTimeSlotSpinner() {
        // TODO change timeSlotsList source with selected meeting room
        List<TimeSlot> timeSlotsList = repository.getTimeSlots();
        TimeSlotSpinnerAdapter timeSlotSpinnerAdapter = new TimeSlotSpinnerAdapter(this,R.layout.item_time_slop_spinner, timeSlotsList);
        timeSlotSpinner.setAdapter(timeSlotSpinnerAdapter);
        timeSlotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeSlot = (TimeSlot) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void configureAddMeetingButton() {
        addMeetingButton.setOnClickListener(view -> {
            // TODO
            long meetingId = buildNewId();
            List<Member> membersForNewMeeting = new ArrayList<>();
            membersForNewMeeting.add(repository.getMembers().get(0));
            membersForNewMeeting.add(repository.getMembers().get(2));
            membersForNewMeeting.add(repository.getMembers().get(5));
            //long id, String meetingTopic, MeetingRoom meetingRoom, String meetingDate, TimeSlot meetingTimeSlot, List<Member> members
            Meeting meeting = new Meeting(
                    meetingId,
                    topicInput.getEditText().getText().toString(),
                    meetingRoom,
                    dateInput.getEditText().getText().toString(),
                    timeSlot,
                    membersForNewMeeting
            );
            repository.addMeeting(meeting);
            finish();
        });
    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    private long buildNewId() {
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
}

package fr.zante.mareu.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * <p>activity called for adding a meeting.</p>
 * <p>Displays the fields to fill in to add a meeting</p>
 * @author Eddy GALMAND
 */
public class AddMeetingActivity extends AppCompatActivity {

    /**
     * ImageView clickable allowing a return to the main activity
     * by closing the current activity
     */
    private ImageView buttonPreviousPage;

    /**
     * allowing to select a date for the meeting to be added
     */
    private DatePicker datePicker;

    /**
     * the date of the meeting to be added
     */
    private MeetingDate meetingDate;

    /**
     * allowing to set a topic for the meeting to be added
     */
    private TextInputLayout topicInput;

    /**
     * displaying the meeting room list available
     * allowing to select one for the meeting to be added
     */
    private Spinner meetingRoomSpinner;

    /**
     * the meeting room of the meeting to be added
     */
    private MeetingRoom meetingRoom;

    /**
     * displaying the time slot list available
     * allowing to select one for the meeting to be added
     */
    private Spinner timeSlotSpinner;

    /**
     * the time slot of the meeting room to be added
     */
    private TimeSlot timeSlot;

    /**
     * ImageView clickable allowing to open AddMemberActivity
     * to set the members for the meeting to be added
     */
    private ImageView addMemberToListButton;

    /**
     * button adding the meeting to data
     */
    private MaterialButton addMeetingButton;

    /**
     * repository allowing data access
     */
    private Repository repository;

    /**
     * List of the members participating to the meeting to be added
     */
    private List<Member> membersForNewMeeting;

    /**
     * The recyclerView which handles the list of member's mail
     */
    private RecyclerView recyclerView;

    /**
     * The adapter which handles the list of member's mail
     * displayed in the dedicated recyclerView
     */
    private MailMemberListAdapter adapter;

    /**
     * request code for activity result from AddMemberActivity
     */
    private static final int ADD_MEMBER_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ADD_MEMBER_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            assert data != null;
            membersForNewMeeting = (List<Member>) data.getSerializableExtra(AddMemberActivity.BUNDLE_LIST_MEMBER_CREATED);
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
        configureRecyclerViewMailMemberList();
        configureAddMeetingButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMailMemberListData();
    }

    /**
     * closing the current activity, allowing a return to ListMeetingActivity
     */
    private void configurePreviousPageButton() {
        buttonPreviousPage.setOnClickListener(view -> {
            finish();
        });
    }

    /**
     * configure the date picker
     * allowing to set the meetingDate for the meeting to be added
     * with the list of meetingRoom
     * and the list of timeSlots available for the selected date
     */
    private void configureDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                long newMeetingDateId = buildNewMeetingDateId();
                meetingDate = new MeetingDate(newMeetingDateId, year, month+1, dayOfMonth, repository.getMeetingRooms());
                getMeetingDateFromAvailableList();
                configureMeetingRoomSpinner();
                configureTimeSlotSpinner();
            }
        });

        long meetingDateId = buildNewMeetingDateId();
        meetingDate = new MeetingDate(meetingDateId, datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth(), repository.getMeetingRooms());
        getMeetingDateFromAvailableList();
    }

    /**
     * get the meetingDate if it already exists
     * in the list of meeting date available
     */
    private void getMeetingDateFromAvailableList() {
        List<MeetingDate> availableListMeetingDate = repository.getMeetingDates();
        for (int i=0; i<availableListMeetingDate.size(); i++) {
            if(availableListMeetingDate.get(i).getYear() == meetingDate.getYear() && availableListMeetingDate.get(i).getMonth() == meetingDate.getMonth() && availableListMeetingDate.get(i).getDay() == meetingDate.getDay()) {
                meetingDate = availableListMeetingDate.get(i);
                break;
            }
        }
    }

    /**
     * adding the meeting date selected
     * to the list of meeting date available in data
     * if it is not yet there
     */
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
    }

    /**
     * displaying the list of meetingRoom of the meetingDate selected
     * allowing to select one for the meeting to be added
     */
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

    /**
     * displaying the list of TimeSlots of the MeetingRoom selected
     * allowing to select one for the meeting to be added
     */
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

    /**
     * configure the recyclerView displaying the list of member's mail
     * participating to the meeting to be added
     */
    private void configureRecyclerViewMailMemberList() {
        recyclerView = findViewById(R.id.add_activity_recycler_view_meeting_member_list);
        adapter = new MailMemberListAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * Updates the list of member's mail
     * participating to the meeting to be added
     */
    private void loadMailMemberListData() {
        adapter.updateMailMemberList(membersForNewMeeting);
    }

    /**
     * configure the button allowing to access to AddMemberActivity
     * send the list of member participating to the meeting
     * send a request code for getting back results
     */
    private void configureAddMemberToListButton() {
        addMemberToListButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddMemberActivity.class);
            Bundle myBundle = new Bundle();
            myBundle.putSerializable("MEMBERS_LIST", (Serializable) membersForNewMeeting);
            intent.putExtra("BUNDLE_MEMBERS_LIST", myBundle);
            startActivityForResult(intent, ADD_MEMBER_ACTIVITY_REQUEST_CODE);
        });
    }

    /**
     * if all the fields are filled
     * add a new meeting date in data (if not existing yet)
     * or update this meeting date (see TimeSlot)
     * add a new meeting in data
     */
    private void configureAddMeetingButton() {
        addMeetingButton.setOnClickListener(view -> {
            if (topicInput.getEditText().getText().toString().equals("")) {
                topicInput.setError("Veuillez saisir un sujet !");
            } else if (membersForNewMeeting.size() == 0) {
                Toast.makeText(this, "Sélectionner les participants !", Toast.LENGTH_SHORT).show();
            } else {
                // CREATE A MEETING DATE:
                addMeetingDateToMyList();

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
            }
        });
    }

    /**
     * allow access to this activity
     * @param activity activity we need to access
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    /**
     * @return an unique Id for the meeting to be added
     */
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

    /**
     * @return an unique Id for the meeting date
     * when a new one is created
     */
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

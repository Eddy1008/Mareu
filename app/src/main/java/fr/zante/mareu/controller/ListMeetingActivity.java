package fr.zante.mareu.controller;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import fr.zante.mareu.R;
import fr.zante.mareu.base.BaseActivity;
import fr.zante.mareu.model.Meeting;

public class ListMeetingActivity extends BaseActivity implements MeetingListAdapter.Listener {

    RecyclerView recyclerView;
    FloatingActionButton addMeetingMenuButton;

    private MeetingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        configureAddMeetingButton();
        configureRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void configureRecyclerView() {
        recyclerView = findViewById(R.id.activity_list_meeting_recycler_view);
        adapter = new MeetingListAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void configureAddMeetingButton() {
        addMeetingMenuButton = findViewById(R.id.activity_list_meeting_add_button);
        addMeetingMenuButton.setOnClickListener(view -> {
            AddMeetingActivity.navigate(this);
        });
    }

    private void loadData() {
        adapter.updateList(getRepository().getMeetings());
    }

    @Override
    public void onClickDelete(Meeting meeting) {
        getRepository().deleteMeeting(meeting);
        loadData();
    }
}
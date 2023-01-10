package fr.zante.mareu.controller;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.base.BaseActivity;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;

public class ListMeetingActivity extends BaseActivity implements MeetingListAdapter.Listener {

    ActionBar aBar;
    Menu myMenu;
    RecyclerView recyclerView;
    FloatingActionButton addMeetingMenuButton;
    private MeetingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        aBar = getSupportActionBar();
        configureAddMeetingButton();
        configureRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        if(myMenu != null) {
            myMenu.getItem(1).getSubMenu().clear();
            updateDateOptionsMenu(myMenu);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.myMenu = menu;
        setNoFilterButton(menu);
        updateDateOptionsMenu(menu);
        updateMeetingRoomOptionsMenu(menu);
        return true;
    }

    public void setNoFilterButton(Menu menu) {
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                loadData();
                return false;
            }
        });
    }

    public void meetingDateOrderedByDateFull(List<MeetingDate> meetingDates) {
        Collections.sort(meetingDates, MeetingDate.meetingDateFullComparator);
    }

    public void updateDateOptionsMenu(Menu menu) {
        List<MeetingDate> meetingDateAvailableList = getRepository().getMeetingDates();
        meetingDateOrderedByDateFull(meetingDateAvailableList);
        int meetingDateListSize = meetingDateAvailableList.size();
        if (meetingDateListSize > 0) {
            for (int i=0; i<meetingDateListSize; i++) {
                String itemTitle = meetingDateAvailableList.get(i).getDay() + "/" +
                        meetingDateAvailableList.get(i).getMonth() + "/" +
                        meetingDateAvailableList.get(i).getYear();
                menu.getItem(1).getSubMenu().add(itemTitle);
                int finalI = i;
                menu.getItem(1).getSubMenu().getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        adapter.updateList(getRepository().getMeetingsByDate(meetingDateAvailableList.get(finalI).getDateFull()));
                        adapter.meetingOrderedByRoom();
                        return false;
                    }
                });
            }
        } else {
            menu.getItem(1).getSubMenu().add("Aucune réunion pour le moment !");
            menu.getItem(1).getSubMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(ListMeetingActivity.this, "Aucune réunion prévues pour le moment", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public void updateMeetingRoomOptionsMenu(Menu menu) {
        List<MeetingRoom> meetingRoomAvailableList = getRepository().getMeetingRooms();
        int meetingRoomListSize = meetingRoomAvailableList.size();
        if (meetingRoomListSize > 0) {
            for (int i=0; i<meetingRoomListSize; i++) {
                menu.getItem(2).getSubMenu().add(meetingRoomAvailableList.get(i).getName());
                int finalI = i;
                menu.getItem(2).getSubMenu().getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        adapter.updateList(getRepository().getMeetingsByRoom(meetingRoomAvailableList.get(finalI).getName()));
                        adapter.meetingOrderedByDate();
                        return false;
                    }
                });
            }
        } else {
            menu.getItem(2).getSubMenu().add("Aucune salle disponible");
            menu.getItem(2).getSubMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(ListMeetingActivity.this, "Aucune salle de réunion disponible", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
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
        setTimeSlotIsFreeToTrue(meeting);
        loadData();
    }

    private void setTimeSlotIsFreeToTrue(Meeting meeting) {
        meeting.getMeetingTimeSlot().setFree(true);
        getRepository().updateMeetingDate(meeting.getMeetingDate());
    }
}
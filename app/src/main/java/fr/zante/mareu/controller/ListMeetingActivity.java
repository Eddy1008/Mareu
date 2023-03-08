package fr.zante.mareu.controller;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

/**
 * <p>Home activity of the application which is displayed when the user opens the app.</p>
 * <p>Displays the list of tasks.</p>
 * @author Eddy GALMAND
 */
public class ListMeetingActivity extends BaseActivity implements MeetingListAdapter.Listener {

    ActionBar aBar;
    Menu myMenu;
    RecyclerView recyclerView;
    FloatingActionButton addMeetingMenuButton;

    /**
     * The adapter which handles the list of meetings
     */
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

    /**
     * Set the button showing all meetings in  the menu
     * @param menu
     */
    public void setNoFilterButton(Menu menu) {
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                loadData();
                return false;
            }
        });
    }

    /**
     * Arrange the list of meeting date in the menu in chronological order
     * @param meetingDates list of meeting date displayed in the menu
     */
    public void meetingDateOrderedByDateFull(List<MeetingDate> meetingDates) {
        Collections.sort(meetingDates, MeetingDate.meetingDateFullComparator);
    }

    /**
     * create the meeting date buttons displayed in the menu
     * @param menu
     */
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

    /**
     * create the meeting room buttons displayed in the menu
     * @param menu
     */
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

    /**
     * configure the meeting list recyclerView
     */
    private void configureRecyclerView() {
        recyclerView = findViewById(R.id.activity_list_meeting_recycler_view);
        adapter = new MeetingListAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * configure the add meeting button
     */
    private void configureAddMeetingButton() {
        addMeetingMenuButton = findViewById(R.id.activity_list_meeting_add_button);
        addMeetingMenuButton.setOnClickListener(view -> {
            AddMeetingActivity.navigate(this);
        });
    }

    /**
     * Update the meeting list to display
     */
    private void loadData() {
        adapter.updateList(getRepository().getMeetings());
    }

    /**
     * Delete the meeting from data,
     * update the time slot isFree to true
     * and update the meeting list displayed
     * @param meeting target to delete from data
     */
    @Override
    public void onClickDelete(Meeting meeting) {
        getRepository().deleteMeeting(meeting);
        setTimeSlotIsFreeToTrue(meeting);
        loadData();
    }

    /**
     * Set the meeting isFree to true
     * update the data
     * @param meeting target that we need to update isFree
     */
    private void setTimeSlotIsFreeToTrue(Meeting meeting) {
        meeting.getMeetingTimeSlot().setFree(true);
        getRepository().updateMeetingDate(meeting.getMeetingDate());
    }
}
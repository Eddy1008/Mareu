package fr.zante.mareu.controller;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Meeting;

/**
 * <p>ViewHolder for meeting items in the meeting list</p>
 * @author Eddy GALMAND
 */
public class ListMeetingViewHolder extends RecyclerView.ViewHolder{

    private ImageView sticker;
    private TextView meetingDate;
    private TextView meetingRoomName;
    private TextView timeSlotBeginning;
    private TextView meetingTopic;
    private TextView members;
    private ImageButton deleteButton;

    /**
     * Instantiates a new ListMeetingViewHolder
     * @param itemView the view of the meeting item
     */
    public ListMeetingViewHolder(@NonNull View itemView) {
        super(itemView);
        sticker = itemView.findViewById(R.id.item_list_meeting_sticker);
        meetingDate = itemView.findViewById(R.id.item_list_meeting_date);
        meetingRoomName = itemView.findViewById(R.id.item_list_meeting_meeting_room_name);
        timeSlotBeginning = itemView.findViewById(R.id.item_list_meeting_timeslot_beginning);
        meetingTopic = itemView.findViewById(R.id.item_list_meeting_topic);
        members = itemView.findViewById(R.id.item_list_meeting_member_list);
        deleteButton = itemView.findViewById(R.id.item_list_meeting_delete_button);
    }

    /**
     * Binds a meeting to the view
     * @param meeting the meeting to bind in the view
     * @param callback the Listener to bind to the delete button
     */
    public void bind(Meeting meeting, MeetingListAdapter.Listener callback) {
        sticker.setImageResource(meeting.getMeetingRoom().getColor());
        String buildStringWithDate = meeting.getMeetingDate().getDay() + "/" + meeting.getMeetingDate().getMonth() + "/" + meeting.getMeetingDate().getYear();
        meetingDate.setText(buildStringWithDate);
        meetingRoomName.setText(meeting.getMeetingRoom().getName());
        timeSlotBeginning.setText(meeting.getMeetingTimeSlot().getBeginning());
        meetingTopic.setText(meeting.getMeetingTopic());
        String memberList = makeStringWithMembers(meeting);
        members.setText(memberList);
        deleteButton.setOnClickListener(view -> callback.onClickDelete(meeting));
    }

    /**
     * @param meeting the meeting of which we need to show the members
     * @return a String containing the members of the meeting
     */
    private String makeStringWithMembers(Meeting meeting) {
        String membersList = "";
        for(int i = 0; i < meeting.getMembers().size(); i++) {
            membersList = membersList + meeting.getMembers().get(i).getMail() + ", ";
        }
        return membersList;
    }
}

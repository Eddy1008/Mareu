package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.MeetingRoom;

/**
 * <p>Adapter which handles the list of meeting room</p>
 * <p>to display in a spinner</p>
 * @author Eddy GALMAND
 */
public class MeetingRoomSpinnerAdapter extends ArrayAdapter<MeetingRoom> {

    private LayoutInflater layoutInflater;

    public MeetingRoomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<MeetingRoom> meetingRooms) {
        super(context, resource, meetingRooms);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.item_meeting_room_spinner, null, true);
        MeetingRoom meetingRoom = getItem(position);
        TextView textview = (TextView)rowView.findViewById(R.id.item_meeting_room_spinner_textview);
        textview.setText(meetingRoom.getName());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_meeting_room_spinner, parent, false);
        }
        MeetingRoom meetingRoom = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.item_meeting_room_spinner_textview);
        textView.setText(meetingRoom.getName());
        return convertView;
    }
}

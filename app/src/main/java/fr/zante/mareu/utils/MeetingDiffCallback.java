package fr.zante.mareu.utils;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import fr.zante.mareu.model.Meeting;

public class MeetingDiffCallback extends DiffUtil.Callback{

    private final List<Meeting> oldMeetings;
    private final List<Meeting> newMeetings;

    public MeetingDiffCallback(List<Meeting> newMeetings, List<Meeting> oldMeetings) {
        this.newMeetings = newMeetings;
        this.oldMeetings = oldMeetings;
    }

    @Override
    public int getOldListSize() { return oldMeetings.size(); }

    @Override
    public int getNewListSize() { return newMeetings.size(); }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMeetings.get(oldItemPosition).getId() == newMeetings.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMeetings.get(oldItemPosition).equals(newMeetings.get(newItemPosition));
    }
}

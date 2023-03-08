package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.utils.MeetingDiffCallback;

/**
 * <p>Adapter which handles the list of meetings to display in the dedicated recyclerView.</p>
 * @author Eddy GALMAND
 */
public class MeetingListAdapter extends RecyclerView.Adapter<ListMeetingViewHolder> {

    /**
     * List of meetings the adapter deals with.
     */
    private List<Meeting> meetings = new ArrayList<>();

    /**
     * The listener for when a meeting needs to be deleted.
     */
    private final Listener callback;

    /**
     * Listener for deleting meetings
     */
    public interface Listener {
        /**
         * Called when a meeting needs to be deleted
         * @param meeting the meeting that needs to be deleted
         */
        void onClickDelete(Meeting meeting);
    }

    /**
     * Instantiates a new MeetingListAdapter
     * @param callback listener for when a meeting needs to be deleted.
     */
    public MeetingListAdapter(Listener callback) { this.callback = callback; }

    @NonNull
    @Override
    public ListMeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_meeting, parent, false);
        return new ListMeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMeetingViewHolder holder, int position) {
        holder.bind(meetings.get(position), callback);
    }

    @Override
    public int getItemCount() { return meetings.size(); }

    /**
     * Arrange the list of meeting by chronological order
     */
    public void meetingOrderedByDate() {
        Collections.sort(meetings, Meeting.meetingDateComparator);
        notifyDataSetChanged();
    }

    /**
     * Arrange the list of meeting by meeting room
     */
    public void meetingOrderedByRoom() {
        Collections.sort(meetings, Meeting.meetingRoomComparator);
        notifyDataSetChanged();
    }


    /**
     * Update the list of meetings the adapter deals with
     * @param newList the list of meetings the adapter deals with
     */
    public void updateList(List<Meeting> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MeetingDiffCallback(newList, this.meetings));
        this.meetings = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }


}

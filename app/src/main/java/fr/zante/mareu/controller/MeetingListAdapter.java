package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.utils.MeetingDiffCallback;

public class MeetingListAdapter extends RecyclerView.Adapter<ListMeetingViewHolder> {

    private List<Meeting> meetings = new ArrayList<>();

    private final Listener callback;
    public interface Listener {
        void onClickDelete(Meeting meeting);
    }

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

    public void updateList(List<Meeting> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MeetingDiffCallback(newList, this.meetings));
        this.meetings = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}

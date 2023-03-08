package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Member;

/**
 * <p>Adapter which handles a list of members </p>
 * <p>to displayed in the dedicated recyclerView.</p>
 * @author Eddy GALMAND
 */
public class MemberListAdapter extends RecyclerView.Adapter<ListMemberViewHolder> {

    /**
     * List of members the adapter deals with.
     */
    private List<Member> members = new ArrayList<>();

    /**
     * List of members created for a new meeting creation
     */
    private List<Member> meetingMemberList = new ArrayList<>();

    /**
     * Listener for when a member needs to be added
     */
    private final AddToList addToListInterface;

    /**
     * Listener for adding members
     */
    public interface AddToList {
        /**
         * Called when a member needs to be added to meetingMemberList
         * @param member the member to add
         * @param imageButton the button we want to customize appearance
         *                    if added yet or not
         */
        void onClickAddToList(Member member, ImageButton imageButton);
    }

    /**
     * Instantiates a new MemberListAdapter
     * @param addToListInterface Listener for when a member needs to be added.
     */
    public MemberListAdapter(AddToList addToListInterface) {this.addToListInterface = addToListInterface;}

    @NonNull
    @Override
    public ListMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_member, parent, false);
        return new ListMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMemberViewHolder holder, int position) {
        holder.bind(meetingMemberList, members.get(position), addToListInterface);
    }

    @Override
    public int getItemCount() { return members.size(); }

    /**
     * update the list of members the adapter deals with.
     * @param membersList the list of members the adapter deals with
     */
    public void updateMemberList(List<Member> membersList) {
        this.members = new ArrayList<>(membersList);
    }

    /**
     * Update the list of members selected for the meeting to be added
     * @param meetingMembersList the list of members for the meeting to be added.
     */
    public void updateMeetingMemberList(List<Member> meetingMembersList) {
        this.meetingMemberList = new ArrayList<>(meetingMembersList);
    }
}

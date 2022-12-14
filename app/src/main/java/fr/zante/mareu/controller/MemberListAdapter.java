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

public class MemberListAdapter extends RecyclerView.Adapter<ListMemberViewHolder> {

    private List<Member> members = new ArrayList<>();
    private List<Member> meetingMemberList = new ArrayList<>();

    private final AddToList addToListInterface;
    public interface AddToList {
        void onClickAddToList(Member member, ImageButton imageButton);
    }

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

    public void updateMemberList(List<Member> membersList) {
        this.members = new ArrayList<>(membersList);
    }

    public void updateMeetingMemberList(List<Member> meetingMembersList) {
        this.meetingMemberList = new ArrayList<>(meetingMembersList);
    }
}

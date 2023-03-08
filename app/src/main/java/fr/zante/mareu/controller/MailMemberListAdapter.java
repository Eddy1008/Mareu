package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Member;

/**
 * <p>Adapter which handles a list of members participating to a meeting </p>
 * <p>to display their mail in the dedicated recyclerView.</p>
 * @author Eddy GALMAND
 */
public class MailMemberListAdapter extends RecyclerView.Adapter<ListMailMemberViewHolder>{

    /**
     * List of members the adapter deals with.
     */
    private List<Member> members = new ArrayList<>();

    @NonNull
    @Override
    public ListMailMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_mail_member, parent, false);
        return new ListMailMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMailMemberViewHolder holder, int position) {
        holder.bind(members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }


    /**
     * update the list displayed in the recyclerView
     * @param memberList the list received to update the one displayed
     */
    public void updateMailMemberList(List<Member> memberList) {
        this.members = new ArrayList<>(memberList);
        notifyDataSetChanged();
    }
}

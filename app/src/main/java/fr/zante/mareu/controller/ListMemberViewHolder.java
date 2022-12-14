package fr.zante.mareu.controller;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Member;

public class ListMemberViewHolder extends RecyclerView.ViewHolder {

    private TextView memberName;
    private TextView memberMail;
    private ImageButton addMemberToListButton;

    public ListMemberViewHolder(@NonNull View itemView) {
        super(itemView);
        memberName = itemView.findViewById(R.id.item_list_member_name);
        memberMail = itemView.findViewById(R.id.item_list_member_mail);
        addMemberToListButton = itemView.findViewById(R.id.item_list_member_add_list_button);
    }

    public void bind(List<Member> myMemberList, Member member, MemberListAdapter.AddToList addToListInterface) {
        memberName.setText(member.getName());
        memberMail.setText(member.getMail());
        for (Member item :myMemberList) {
            if (item.getMail().equals(member.getMail())) {
                addMemberToListButton.setImageResource(R.drawable.ic_baseline_group_add_36_actif);
            }
        }
        addMemberToListButton.setOnClickListener(view -> addToListInterface.onClickAddToList(member, addMemberToListButton));
    }
}

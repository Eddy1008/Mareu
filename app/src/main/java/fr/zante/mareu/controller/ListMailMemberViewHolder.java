package fr.zante.mareu.controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Member;

public class ListMailMemberViewHolder extends RecyclerView.ViewHolder{

    private TextView memberMail;

    public ListMailMemberViewHolder(@NonNull View itemView) {
        super(itemView);
        memberMail = itemView.findViewById(R.id.item_list_mail_member_mail);
    }

    public void bind(Member member) {
        memberMail.setText(member.getMail());
    }
}

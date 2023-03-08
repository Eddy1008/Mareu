package fr.zante.mareu.controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Member;

/**
 * <p>ViewHolder for member's mail items in the list</p>
 * @author Eddy GALMAND
 */
public class ListMailMemberViewHolder extends RecyclerView.ViewHolder{

    private TextView memberMail;

    /**
     * Instantiates a new ListMailMemberViewHolder
     * @param itemView the view of the member mail's item
     */
    public ListMailMemberViewHolder(@NonNull View itemView) {
        super(itemView);
        memberMail = itemView.findViewById(R.id.item_list_mail_member_mail);
    }

    /**
     * Binds a member's mail to the view
     * @param member the member whose email we want to bind
     */
    public void bind(Member member) {
        memberMail.setText(member.getMail());
    }
}

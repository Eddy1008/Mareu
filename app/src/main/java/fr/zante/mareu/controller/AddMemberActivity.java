package fr.zante.mareu.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.di.Injection;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.repository.Repository;

/**
 * <p>Activity called to build a list of members </p>
 * <p>participating to a new meeting to be added</p>
 * @author Eddy GALMAND
 */
public class AddMemberActivity extends AppCompatActivity implements MemberListAdapter.AddToList{

    /**
     * ImageView clickable allowing a return to the previous activity
     * by closing the current activity
     */
    private ImageView previousPageButton;

    /**
     * The recyclerView which handles the list of members
     */
    private RecyclerView recyclerView;

    /**
     * Button used to validate the list built
     */
    private FloatingActionButton addMemberButton;

    /**
     *
     */
    private MemberListAdapter memberListAdapter;
    private Repository repository;
    private List<Member> membersList;

    public static final String BUNDLE_LIST_MEMBER_CREATED = "BUNDLE_LIST_MEMBER_CREATED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        previousPageButton = findViewById(R.id.add_member_activity_previous_page_button);

        repository = Injection.getRepository();

        configurePreviousPageButton();
        getMembersListData();
        configureMemberListRecyclerView();
        configureAddMemberButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMemberListData();
    }

    private void configurePreviousPageButton() {
        previousPageButton.setOnClickListener(view -> {
            finish();
        });
    }

    private void getMembersListData() {
        Intent intent = getIntent();
        Bundle myBundle = intent.getBundleExtra("BUNDLE_MEMBERS_LIST");
        membersList = (List<Member>) myBundle.get("MEMBERS_LIST");
    }

    private void configureMemberListRecyclerView() {
        recyclerView = findViewById(R.id.add_member_activity_recycler_view);
        memberListAdapter = new MemberListAdapter(this);
        recyclerView.setAdapter(memberListAdapter);
    }

    private void configureAddMemberButton() {
        addMemberButton = findViewById(R.id.add_member_activity_add_button);
        addMemberButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddMeetingActivity.class);
            intent.putExtra(BUNDLE_LIST_MEMBER_CREATED, (Serializable) membersList);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void loadMemberListData() {
        memberListAdapter.updateMemberList(repository.getMembers());
        memberListAdapter.updateMeetingMemberList(membersList);
    }

    @Override
    public void onClickAddToList(Member member, ImageButton imageButton) {
        Boolean isInTheList = false;
        for (int i=0; i< membersList.size(); i++) {
            if (member.getMail().equals(membersList.get(i).getMail())) {
                membersList.remove(membersList.get(i));
                isInTheList = true;
                break;
            }
        }
        if (!isInTheList) {
            membersList.add(member);
        }
        setAddToListButtonAppearance(imageButton, membersList.contains(member));
    }

    public void setAddToListButtonAppearance(ImageButton imageButton, Boolean isSelected) {
        if(isSelected) {
            imageButton.setImageResource(R.drawable.ic_baseline_group_add_36_actif);
        } else {
            imageButton.setImageResource(R.drawable.ic_baseline_group_add_36);
        }
    }
}
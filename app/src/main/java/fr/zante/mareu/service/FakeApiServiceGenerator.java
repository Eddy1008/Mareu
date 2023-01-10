package fr.zante.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;

public class FakeApiServiceGenerator {

    static List<Member> generateMembersList() {return new ArrayList<>(MEMBERS_LIST);}
    public static List<Member> MEMBERS_LIST = Arrays.asList(
            new Member(0, "Eddy", "eddy@mareu.com"),
            new Member(1, "Ethan", "Ethan@mareu.com"),
            new Member(2, "Melody", "Melody@mareu.com"),
            new Member(3, "Mael", "Mael@mareu.com"),
            new Member(4, "Tom", "Tom@mareu.com"),
            new Member(5, "Cassandre", "Cassandre@mareu.com"),
            new Member(6, "Jules", "Jules@mareu.com"),
            new Member(7, "Ivy", "Ivy@mareu.com"),
            new Member(8, "Julien", "Julien@mareu.com")
    );

    public static List<TimeSlot> generateTimeSlotsList() {
        return Arrays.asList(
                new TimeSlot(0,"8H00",true),
                new TimeSlot(1,"9H00",true),
                new TimeSlot(2,"10H00",true),
                new TimeSlot(3,"11H00",true),
                new TimeSlot(4,"12H00",true),
                new TimeSlot(5,"13H00",true),
                new TimeSlot(6,"14H00",true),
                new TimeSlot(7,"15H00",true),
                new TimeSlot(8,"16H00",true),
                new TimeSlot(9,"17H00",true)
        );
    }

    public static List<MeetingRoom> generateMeetingRoomsList() {
        return Arrays.asList(
                new MeetingRoom(0, "Reunion A", R.drawable.ic_baseline_circle_60_room_a, generateTimeSlotsList()),
                new MeetingRoom(1, "Reunion B", R.drawable.ic_baseline_circle_60_room_b, generateTimeSlotsList()),
                new MeetingRoom(2, "Reunion C", R.drawable.ic_baseline_circle_60_room_c, generateTimeSlotsList()),
                new MeetingRoom(3, "Reunion D", R.drawable.ic_baseline_circle_60_room_d, generateTimeSlotsList()),
                new MeetingRoom(4, "Reunion E", R.drawable.ic_baseline_circle_60_room_e, generateTimeSlotsList()),
                new MeetingRoom(5, "Reunion F", R.drawable.ic_baseline_circle_60_room_f, generateTimeSlotsList()),
                new MeetingRoom(6, "Reunion G", R.drawable.ic_baseline_circle_60_room_g, generateTimeSlotsList()),
                new MeetingRoom(7, "Reunion H", R.drawable.ic_baseline_circle_60_room_h, generateTimeSlotsList()),
                new MeetingRoom(8, "Reunion I", R.drawable.ic_baseline_circle_60_room_i, generateTimeSlotsList()),
                new MeetingRoom(9, "Reunion J", R.drawable.ic_baseline_circle_60_room_j, generateTimeSlotsList())
        );
    }

    static List<MeetingDate> generateMeetingDates() {return new ArrayList<>(MEETING_DATE_LIST);}
    public static List<MeetingDate> MEETING_DATE_LIST = Arrays.asList();

    static List<Meeting> generateMeetingsList() {return new ArrayList<>(MEETINGS_LIST);}
    public static List<Meeting> MEETINGS_LIST = Arrays.asList();


    public static List<Member> generateMembersForMeeting() {
        List<Member> membersForMeeting = new ArrayList<>();
        int min = 2;
        int max = 4;
        int randomMeetingMembersNumber = (int) Math.floor(Math.random()*(max-min+1)+min);
        for (int i=0; i<randomMeetingMembersNumber; i++) {
            Member memberToAdd = newRandomMember();
            membersForMeeting.add(memberToAdd);
        }
        return membersForMeeting;
    }

    static Member newRandomMember() {
        // create a random index:
        int randomFakeMemberIndex = (int) (Math.random() * (MEMBERS_LIST.size()));
        // get user with the random index from the list:
        Member randomMemberToAdd = MEMBERS_LIST.get(randomFakeMemberIndex);
        //return a user
        return randomMemberToAdd;
    }
}

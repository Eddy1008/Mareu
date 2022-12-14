package fr.zante.mareu.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    static List<TimeSlot> generateTimeSlotsList() {
        List<TimeSlot> newTimeSlotList = new ArrayList<>();
        for (TimeSlot item : TIME_SLOTS_LIST) {
            newTimeSlotList.add(item.cloneTimeSlot());
        }
        return newTimeSlotList;
    }
    public static List<TimeSlot> TIME_SLOTS_LIST = Arrays.asList(
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

    static List<MeetingRoom> generateMeetingRoomsList() {
        List<MeetingRoom> newMeetingRoomList = new ArrayList<>();
        for (MeetingRoom item : MEETING_ROOMS_LIST) {
            newMeetingRoomList.add(item.cloneMeetingRoom());
        }
        return newMeetingRoomList;
    }
    public static List<MeetingRoom> MEETING_ROOMS_LIST = Arrays.asList(
            new MeetingRoom(0, "Reunion A", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(1, "Reunion B", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(2, "Reunion C", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(3, "Reunion D", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(4, "Reunion E", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(5, "Reunion F", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(6, "Reunion G", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(7, "Reunion H", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(8, "Reunion I", "#FF4444", generateTimeSlotsList()),
            new MeetingRoom(9, "Reunion J", "#FF4444", generateTimeSlotsList())
    );

    static List<MeetingDate> generateMeetingDates() {return new ArrayList<>(MEETING_DATE_LIST);}
    public static List<MeetingDate> MEETING_DATE_LIST = Arrays.asList();

    static List<Meeting> generateMeetingsList() {return new ArrayList<>(MEETINGS_LIST);}
    public static List<Meeting> MEETINGS_LIST = Arrays.asList();

    /**
    static List<Member> generateMembersForMeeting() {
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
     */

    /**
    static Member newRandomMember() {
        // create a random index:
        int randomFakeMemberIndex = (int) (Math.random() * (MEMBERS_LIST.size()));
        // get user with the random index from the list:
        Member randomMemberToAdd = MEMBERS_LIST.get(randomFakeMemberIndex);
        //return a user
        return randomMemberToAdd;
    }
     */
}

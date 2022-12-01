package fr.zante.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.zante.mareu.model.Meeting;
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

    static List<TimeSlot> generateTimeSlotsList() {return new ArrayList<>(TIME_SLOTS_LIST);}
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


    static List<MeetingRoom> generateMeetingRoomsList() {return new ArrayList<>(MEETING_ROOMS_LIST);}
    public static List<MeetingRoom> MEETING_ROOMS_LIST = Arrays.asList(
            new MeetingRoom(0, "Reunion A", "#FF4444"),
            new MeetingRoom(1, "Reunion B", "#FF4444"),
            new MeetingRoom(2, "Reunion C", "#FF4444"),
            new MeetingRoom(3, "Reunion D", "#FF4444"),
            new MeetingRoom(4, "Reunion E", "#FF4444"),
            new MeetingRoom(5, "Reunion F", "#FF4444"),
            new MeetingRoom(6, "Reunion G", "#FF4444"),
            new MeetingRoom(7, "Reunion H", "#FF4444"),
            new MeetingRoom(8, "Reunion I", "#FF4444"),
            new MeetingRoom(9, "Reunion J", "#FF4444")
    );

    static List<Member> members1 = generateMembersForMeeting();
    static List<Member> members2 = generateMembersForMeeting();
    static List<Member> members3 = generateMembersForMeeting();
    static List<Member> members4 = generateMembersForMeeting();
    static List<Member> members5 = generateMembersForMeeting();
    static List<Member> members6 = generateMembersForMeeting();
    static List<Member> members7 = generateMembersForMeeting();
    static List<Member> members8 = generateMembersForMeeting();

    static List<Meeting> generateMeetingsList() {return new ArrayList<>(MEETINGS_LIST);}
    public static List<Meeting> MEETINGS_LIST = Arrays.asList(
            new Meeting(0, "Peach", MEETING_ROOMS_LIST.get(0), "10-11-2022", TIME_SLOTS_LIST.get(0), members1),
            new Meeting(1, "Daisy", MEETING_ROOMS_LIST.get(0), "10-11-2022", TIME_SLOTS_LIST.get(1), members2),
            new Meeting(2, "Mario", MEETING_ROOMS_LIST.get(2), "11-11-2022", TIME_SLOTS_LIST.get(2), members3),
            new Meeting(3, "Wario", MEETING_ROOMS_LIST.get(2), "11-11-2022", TIME_SLOTS_LIST.get(3), members4),
            new Meeting(4, "Luigi", MEETING_ROOMS_LIST.get(4), "12-11-2022", TIME_SLOTS_LIST.get(4), members5),
            new Meeting(5, "Waluigi", MEETING_ROOMS_LIST.get(4), "12-11-2022", TIME_SLOTS_LIST.get(5), members6),
            new Meeting(6, "Bowser", MEETING_ROOMS_LIST.get(6), "13-11-2022", TIME_SLOTS_LIST.get(6), members7),
            new Meeting(7, "Toad", MEETING_ROOMS_LIST.get(8), "14-11-2022", TIME_SLOTS_LIST.get(8), members8)
    );

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

    static Member newRandomMember() {
        // create a random index:
        int randomFakeMemberIndex = (int) (Math.random() * (MEMBERS_LIST.size()));
        // get user with the random index from the list:
        Member randomMemberToAdd = MEMBERS_LIST.get(randomFakeMemberIndex);
        //return a user
        return randomMemberToAdd;
    }
}

package fr.zante.mareu.model;

import java.util.Comparator;
import java.util.List;

/**
 * <p>Model object representing a Meeting</p>
 * @author Eddy GALMAND
 */
public class Meeting {

    private long id;
    private String meetingTopic;
    private MeetingRoom meetingRoom;
    private MeetingDate meetingDate;
    private TimeSlot meetingTimeSlot;
    private List<Member> members;

    // Constructor

    public Meeting(long id, String meetingTopic, MeetingRoom meetingRoom, MeetingDate meetingDate, TimeSlot meetingTimeSlot, List<Member> members) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
        this.meetingTimeSlot = meetingTimeSlot;
        this.members = members;
    }

    public static Comparator<Meeting> meetingDateComparator = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.getMeetingDate().getDateFull() - m2.getMeetingDate().getDateFull();
        }
    };

    public static Comparator<Meeting> meetingRoomComparator = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.getMeetingRoom().getName().compareTo(m2.getMeetingRoom().getName());
        }
    };

    // Getters & Setters

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getMeetingTopic() {return meetingTopic;}
    public void setMeetingTopic(String meetingTopic) {this.meetingTopic = meetingTopic;}

    public MeetingRoom getMeetingRoom() {return meetingRoom;}
    public void setMeetingRoom(MeetingRoom meetingRoom) {this.meetingRoom = meetingRoom;}

    public MeetingDate getMeetingDate() {return meetingDate;}
    public void setMeetingDate(MeetingDate meetingDate) {this.meetingDate = meetingDate;}

    public TimeSlot getMeetingTimeSlot() {return meetingTimeSlot;}
    public void setMeetingTimeSlot(TimeSlot meetingTimeSlot) {this.meetingTimeSlot = meetingTimeSlot;}

    public List<Member> getMembers() {return members;}
    public void setMembers(List<Member> members) {this.members = members;}
}

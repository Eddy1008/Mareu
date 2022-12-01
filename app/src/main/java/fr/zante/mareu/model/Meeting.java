package fr.zante.mareu.model;

import java.util.List;

/**
 * Model object representing a Meeting.
 */
public class Meeting {

    private long id;
    private String meetingTopic;
    private MeetingRoom meetingRoom;
    private String meetingDate;
    private TimeSlot meetingTimeSlot;
    private List<Member> members;

    /**
     * Constructor
     */
    public Meeting(long id, String meetingTopic, MeetingRoom meetingRoom, String meetingDate, TimeSlot meetingTimeSlot, List<Member> members) {
        this.id = id;
        this.meetingTopic = meetingTopic;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
        this.meetingTimeSlot = meetingTimeSlot;
        this.members = members;
    }

    /**
     * Getters & Setters
     */
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getMeetingTopic() {return meetingTopic;}
    public void setMeetingTopic(String meetingTopic) {this.meetingTopic = meetingTopic;}

    public MeetingRoom getMeetingRoom() {return meetingRoom;}
    public void setMeetingRoom(MeetingRoom meetingRoom) {this.meetingRoom = meetingRoom;}

    public String getMeetingDate() {return meetingDate;}
    public void setMeetingDate(String meetingDate) {this.meetingDate = meetingDate;}

    public TimeSlot getMeetingTimeSlot() {return meetingTimeSlot;}
    public void setMeetingTimeSlot(TimeSlot meetingTimeSlot) {this.meetingTimeSlot = meetingTimeSlot;}

    public List<Member> getMembers() {return members;}
    public void setMembers(List<Member> members) {this.members = members;}
}

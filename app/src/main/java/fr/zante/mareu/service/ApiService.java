package fr.zante.mareu.service;

import java.util.List;

import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;

public interface ApiService {
    List<TimeSlot> getTimeSlots();

    List<Member> getMembers();

    List<MeetingRoom> getMeetingRooms();

    List<MeetingDate> getMeetingDates();
    void addMeetingDate(MeetingDate meetingDate);
    void updateMeetingDate(MeetingDate meetingDate);

    List<Meeting> getMeetings();
    List<Meeting> getMeetingsByRoom(String meetingRoomName);
    List<Meeting> getMeetingsByDate(int dateFull);
    void addMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
}

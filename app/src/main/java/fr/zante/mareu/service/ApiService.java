package fr.zante.mareu.service;

import java.util.List;

import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;

public interface ApiService {
    List<TimeSlot> getTimeSlots();
    List<Member> getMembers();
    List<MeetingRoom> getMeetingRooms();
    List<Meeting> getMeetings();
    void addMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
}

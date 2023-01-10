package fr.zante.mareu.repository;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;
import fr.zante.mareu.service.ApiService;

public class Repository {
    private final ApiService apiService;

    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<TimeSlot> getTimeSlots() {
        List<TimeSlot> timeSlots = apiService.getTimeSlots();
        return timeSlots;
    }

    public List<Member> getMembers() {
        List<Member> members = apiService.getMembers();
        return members;
    }

    public List<MeetingRoom> getMeetingRooms() {
        List<MeetingRoom> meetingRooms = apiService.getMeetingRooms();
        return meetingRooms;
    }

    /**
     * MeetingDate services :
     */
    public List<MeetingDate> getMeetingDates() {
        List<MeetingDate> meetingDates = apiService.getMeetingDates();
        return meetingDates;
    }

    public void addMeetingDate(MeetingDate meetingDate) {apiService.addMeetingDate(meetingDate);}

    public void updateMeetingDate(MeetingDate meetingDate) {apiService.updateMeetingDate(meetingDate);}


    /**
     * Meeting services :
     */
    public List<Meeting> getMeetings() {
        List<Meeting> meetings = apiService.getMeetings();
        return meetings;
    }

    public List<Meeting> getMeetingsByRoom(String meetingRoomName) {
        List<Meeting> meetingsByRoomList = apiService.getMeetingsByRoom(meetingRoomName);
        return meetingsByRoomList;
    }

    public List<Meeting> getMeetingsByDate(int dateFull) {
        List<Meeting> meetingsByDateList = apiService.getMeetingsByDate(dateFull);
        return meetingsByDateList;
    }

    public void addMeeting(Meeting meeting) {
        apiService.addMeeting(meeting);
    }

    public void deleteMeeting (Meeting meeting) {
        apiService.deleteMeeting(meeting);
    }
}

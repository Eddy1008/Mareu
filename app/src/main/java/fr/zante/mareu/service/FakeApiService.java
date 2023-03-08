package fr.zante.mareu.service;

import java.util.ArrayList;
import java.util.List;

import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;

/**
 * Service used by the repository for data access
 * implements the ApiService interface to define the operation of its methods
 * @author Eddy GALMAND
 */
public class FakeApiService implements ApiService{

    private List<TimeSlot> timeSlotList = FakeApiServiceGenerator.generateTimeSlotsList();
    private List<Member> memberList = FakeApiServiceGenerator.generateMembersList();
    private List<MeetingRoom> meetingRoomList = FakeApiServiceGenerator.generateMeetingRoomsList();

    private List<MeetingDate> meetingDateList = FakeApiServiceGenerator.generateMeetingDates();

    private List<Meeting> meetings = FakeApiServiceGenerator.generateMeetingsList();

    @Override
    public List<TimeSlot> getTimeSlots() {
        return timeSlotList;
    }

    @Override
    public List<Member> getMembers() {
        return memberList;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return FakeApiServiceGenerator.generateMeetingRoomsList();
    }


    // For the MeetingDate management
    @Override
    public List<MeetingDate> getMeetingDates() {
        return meetingDateList;
    }

    @Override
    public void addMeetingDate(MeetingDate meetingDate) {
        meetingDateList.add(meetingDate);
    }

    @Override
    public void updateMeetingDate(MeetingDate meetingDate) {
        int indexMeetingDate = meetingDateList.indexOf(meetingDate);
        meetingDateList.set(indexMeetingDate, meetingDate);
    }


    // For the Meeting management:
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(String meetingRoomName) {
        List<Meeting> meetingsByRoomList = new ArrayList<>();
        for (Meeting meeting: meetings) {
            if (meetingRoomName.equals(meeting.getMeetingRoom().getName())) {
                meetingsByRoomList.add(meeting);
            }
        }
        return meetingsByRoomList;
    }

    @Override
    public List<Meeting> getMeetingsByDate(int dateFull) {
        List<Meeting> meetingsByDateList = new ArrayList<>();
        for (Meeting meeting: meetings) {
            if (dateFull == meeting.getMeetingDate().getDateFull()) {
                meetingsByDateList.add(meeting);
            }
        }
        return meetingsByDateList;
    }


    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }
}

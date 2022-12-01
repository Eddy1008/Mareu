package fr.zante.mareu.service;

import java.util.List;

import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;

public class FakeApiService implements ApiService{

    private List<TimeSlot> timeSlotList = FakeApiServiceGenerator.generateTimeSlotsList();
    private List<Member> memberList = FakeApiServiceGenerator.generateMembersList();
    private List<MeetingRoom> meetingRoomList = FakeApiServiceGenerator.generateMeetingRoomsList();
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
        return meetingRoomList;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
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

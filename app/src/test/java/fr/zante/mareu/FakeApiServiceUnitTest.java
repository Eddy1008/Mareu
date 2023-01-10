package fr.zante.mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import fr.zante.mareu.di.Injection;
import fr.zante.mareu.model.Meeting;
import fr.zante.mareu.model.MeetingDate;
import fr.zante.mareu.model.MeetingRoom;
import fr.zante.mareu.model.Member;
import fr.zante.mareu.model.TimeSlot;
import fr.zante.mareu.repository.Repository;
import fr.zante.mareu.service.FakeApiServiceGenerator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FakeApiServiceUnitTest {

    private Repository repository;

    @Before
    public void setup() { repository = Injection.createRepository(); }

    @Test
    public void  getTimeSlotsWithSuccess() {
        List<TimeSlot> timeSlots = repository.getTimeSlots();
        List<TimeSlot> timeSlotsExpectedList = FakeApiServiceGenerator.generateTimeSlotsList();
        assertThat(timeSlots, IsIterableContainingInAnyOrder.containsInAnyOrder(timeSlotsExpectedList.toArray()));
    }

    @Test
    public void getMeetingRoomsWithSuccess() {
        List<MeetingRoom> meetingRooms = repository.getMeetingRooms();
        List<MeetingRoom> meetingRoomsExpectedList = FakeApiServiceGenerator.generateMeetingRoomsList();
        assertThat(meetingRooms, IsIterableContainingInAnyOrder.containsInAnyOrder(meetingRoomsExpectedList.toArray()));
    }

    @Test
    public void getMembersWithSuccess() {
        List<Member> members = repository.getMembers();
        List<Member> membersExpectedList = FakeApiServiceGenerator.MEMBERS_LIST;
        assertThat(members, IsIterableContainingInAnyOrder.containsInAnyOrder(membersExpectedList.toArray()));
    }

    @Test
    public void getMeetingDatesWithSuccess() {
        List<MeetingDate> meetingDates = repository.getMeetingDates();
        List<MeetingDate> meetingDatesExpectedList = FakeApiServiceGenerator.MEETING_DATE_LIST;
        assertThat(meetingDates, IsIterableContainingInAnyOrder.containsInAnyOrder(meetingDatesExpectedList.toArray()));
    }

    @Test
    public void addMeetingDateWithSuccess() {
        repository.getMeetingDates().clear();
        MeetingDate meetingDate = new MeetingDate(
                01,
                2022,
                12,
                25,
                repository.getMeetingRooms());
        repository.addMeetingDate(meetingDate);
        assertEquals(1, repository.getMeetingDates().size());
    }

    @Test
    public void updateMeetingDateWithSuccess() {
        // TODO
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = repository.getMeetings();
        List<Meeting> meetingsExpectedList = FakeApiServiceGenerator.MEETINGS_LIST;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(meetingsExpectedList.toArray()));
    }

    @Test
    public void addMeetingWithSuccess() {
        MeetingDate meetingDate = new MeetingDate(
                01,
                2022,
                12,
                25,
                repository.getMeetingRooms()
        );

        repository.getMeetings().clear();
        Meeting meeting = new Meeting(
                01,
                "Noël",
                meetingDate.getMeetingRooms().get(0),
                meetingDate,
                meetingDate.getMeetingRooms().get(0).getTimeSlots().get(0),
                FakeApiServiceGenerator.generateMembersForMeeting()
        );

        repository.addMeeting(meeting);
        assertEquals(1, repository.getMeetings().size());
    }

    @Test
    public void deleteMeetingWithSuccess() {
        // TODO
    }


}
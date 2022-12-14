package fr.zante.mareu.model;

import java.util.ArrayList;
import java.util.List;

public class MeetingDate {

    private long id;

    // TODO A changer avec objet Date ou LocalDate
    private int year;
    private int month;
    private int day;

    private List<MeetingRoom> meetingRooms;

    public MeetingDate(long id, int year, int month, int day, List<MeetingRoom> meetingRooms) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.meetingRooms = meetingRooms;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public int getYear() {return year;}
    public void setYear(int year) {this.year = year;}

    public int getMonth() {return month;}
    public void setMonth(int month) {this.month = month;}

    public int getDay() {return day;}
    public void setDay(int day) {this.day = day;}

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }
    public void setMeetingRooms(List<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
    }
}

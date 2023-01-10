package fr.zante.mareu.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeetingDate {

    private long id;

    private int year;
    private int month;
    private int day;
    private int dateFull;

    private List<MeetingRoom> meetingRooms;

    public MeetingDate(long id, int year, int month, int day, List<MeetingRoom> meetingRooms) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.meetingRooms = meetingRooms;
    }

    public static Comparator<MeetingDate> meetingDateFullComparator = new Comparator<MeetingDate>() {
        @Override
        public int compare(MeetingDate m1, MeetingDate m2) {
            return m1.getDateFull() - m2.getDateFull();
        }
    };

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

    public int getDateFull() {
        String dateFullDay = String.valueOf(this.day);
        if (this.day < 10) { dateFullDay = "0" + dateFullDay; }
        String dateFullMonth = String.valueOf(this.month);
        if (this.month < 10) { dateFullMonth = "0" + dateFullMonth; }
        String dateFullYear = String.valueOf(this.year);
        String dateToCompare = dateFullYear + dateFullMonth + dateFullDay;
        dateFull = Integer.parseInt(dateToCompare);
        return dateFull;
    }
    public void setDateFull(int dateFull) {this.dateFull = dateFull;}
}

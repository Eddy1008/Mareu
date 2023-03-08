package fr.zante.mareu.model;

import java.util.List;

/**
 * <p>Model object representing a Meeting room</p>
 * @author Eddy GALMAND
 */
public class MeetingRoom {

    private int id;
    private String name;
    private int color;
    private List<TimeSlot> timeSlots;

    // Constructor

    public MeetingRoom(int id, String name, int color, List<TimeSlot> timeSlots) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.timeSlots = timeSlots;
    }

    // Getters & Setters

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getColor() {return color;}
    public void setColor(int color) {this.color = color;}

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }
    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public MeetingRoom cloneMeetingRoom() {
        return new MeetingRoom(this.getId(), this.getName(), this.getColor(), this.getTimeSlots());
    }
}

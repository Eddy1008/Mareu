package fr.zante.mareu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model object representing a Meeting room
 */
public class MeetingRoom {

    private int id;
    private String name;
    private String color;

    private List<TimeSlot> timeSlots;

    /**
     * Constructor
     */
    public MeetingRoom(int id, String name, String color, List<TimeSlot> timeSlots) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.timeSlots = timeSlots;
    }

    /**
     * Getters & Setters
     */
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

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

package fr.zante.mareu.model;

/**
 * Model object representing a Time slot
 */
public class TimeSlot {

    private int id;
    private String beginning;
    private boolean isFree;

    /**
     * Constructor
     */
    public TimeSlot(int id, String beginning, boolean isFree) {
        this.id = id;
        this.beginning = beginning;
        this.isFree = isFree;
    }

    /**
     * Getters & Setters
     */
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getBeginning() {return beginning;}
    public void setBeginning(String beginning) {this.beginning = beginning;}

    public boolean isFree() {return isFree;}
    public void setFree(boolean free) {isFree = free;}
}

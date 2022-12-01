package fr.zante.mareu.model;

/**
 * Model object representing a member
 */
public class Member {

    private long id;
    private String name;
    private String mail;

    /**
     * Constructor
     */
    public Member(long id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    /**
     * Getters & Setters
     */
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
}

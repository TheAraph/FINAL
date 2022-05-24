package com.example.afinal;

public class Submissions {
    //Variable initialisation
    String eventName;
    String eventDate;
    String eventPrice;
    String eventDescription;
    //Constructors for event details
    public Submissions(String eventName, String eventDate, String eventPrice, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventPrice = eventPrice;
        this.eventDescription = eventDescription;
    }
    //Getters for event details
    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}

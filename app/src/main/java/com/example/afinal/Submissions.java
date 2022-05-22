package com.example.afinal;

public class Submissions {

    String eventName;
    String eventDate;
    String eventPrice;
    String eventDescription;

    public Submissions(String eventName, String eventDate, String eventPrice, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventPrice = eventPrice;
        this.eventDescription = eventDescription;
    }

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

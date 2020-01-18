package com.exer.calendarfy.model;

public class Event {
    private String eventTitle;
    private String eventDesc;

    public Event(String eventTitle, String eventDesc) {
        this.eventTitle = eventTitle;
        this.eventDesc = eventDesc;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }
}

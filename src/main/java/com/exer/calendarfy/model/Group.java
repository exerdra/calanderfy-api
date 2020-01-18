package com.exer.calendarfy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Groups")
public class Group {
    @Id
    private String id;

    private String groupName;

    private ArrayList<String> groupUsers = new ArrayList<>();

    private ArrayList<Event> groupEvents = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<String> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(ArrayList<String> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public ArrayList<Event> getGroupEvents() {
        return groupEvents;
    }

    public void setGroupEvents(ArrayList<Event> groupEvents) {
        this.groupEvents = groupEvents;
    }
}

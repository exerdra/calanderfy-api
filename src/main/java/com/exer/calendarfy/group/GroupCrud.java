package com.exer.calendarfy.group;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.Group;

import java.util.ArrayList;

public interface GroupCrud {
    boolean createNewGroup(String groupName, String creator);
    Group getGroupByGroupName(String groupName);
    ArrayList<Event> getEventsForGroup(String groupName);
    boolean addEventToGroup(String groupName, Event event);
    boolean removeEventFromGroup(String groupName, Event event);
    boolean addUserToGroup(String profileEmail, String groupName);
    boolean removeUserFromGroup(String profileEmail, String groupName);
}

package com.exer.calendarfy.dao.group;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.Group;

public interface CustomGroupRepo {
    void updateGroupWithEvent(Group group, Event event);
    void removeEventFromGroup(Group group, Event event);
    void addUserToGroup(Group group, String profileEmail);
    void removeUserFromGroup(Group group, String profileEmail);
}

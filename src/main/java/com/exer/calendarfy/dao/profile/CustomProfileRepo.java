package com.exer.calendarfy.dao.custom;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.UserProfile;

public interface CustomProfileRepo {
    void updateProfileWithEvent(UserProfile profile, Event event);
    void deleteEventForProfile(UserProfile profile, Event event);
    void updateProfileWithDeviceToken(UserProfile profile, String deviceToken);
    void updateProfileWithAuthorizedEmail(UserProfile profile, String authorizedEmail);
    void deleteAuthorizedUser(UserProfile profile, String authorizedEmail);
    void addUserToGroup(UserProfile profile, String groupName);
    void removeUserFromGroup(UserProfile profile, String groupName);
}

package com.exer.calendarfy.profile;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.UserProfile;

import java.util.List;

public interface ProfileCrud {
    UserProfile getProfileByEmail(String profileEmail);
    void addEventToProfile(String profileEmail, Event event);
    void updateProfile(String profileEmail, String deviceToken);
    List<UserProfile> getAllProfiles();
    void deleteEventForProfile(String profileEmail, Event event);
    void addAuthorizedUserForProfile(String profileEmail, String authorizedEmail);
    void deleteAuthorizedUser(String profileEmail, String authorizedEmail);
}

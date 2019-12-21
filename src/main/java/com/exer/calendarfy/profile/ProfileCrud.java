package com.exer.calendarfy.profile;

import com.exer.calendarfy.data.Event;
import com.exer.calendarfy.data.UserProfile;

public interface ProfileCrud {
    UserProfile getProfileByEmail(String profileEmail);
    void addEventToProfile(String profileEmail, Event event);
}

package com.exer.calendarfy.profile;

import com.exer.calendarfy.dao.custom.CustomProfileRepo;
import com.exer.calendarfy.dao.profile.ProfileRepository;
import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.UserProfile;
import com.exer.calendarfy.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileCrudImpl implements ProfileCrud {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CustomProfileRepo customProfileRepo;

    @Override
    public UserProfile getProfileByEmail(String profileEmail) {
        return profileRepository.findFirstByProfileEmail(profileEmail);
    }

    @Override
    public void addEventToProfile(String profileEmail, Event event) {
        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email, creating new profile");
            createProfileWithEvent(profileEmail, event);
        } else {
            Log.d("Adding event to profile");
            addEventToProfile(profile, event);
        }
    }

    @Override
    public void updateProfile(String profileEmail, String deviceToken) {
        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email, creating new profile");
            registerNewProfile(profileEmail, deviceToken);
        } else {
            Log.d("Updating device token to profile");
            updateDeviceToken(profile, deviceToken);
        }
    }

    @Override
    public List<UserProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public void deleteEventForProfile(String profileEmail, Event event) {
        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email");
        } else {
            Log.d("Removing event for profile");
            deleteEvent(profile, event);
        }
    }

    @Override
    public void addAuthorizedUserForProfile(String profileEmail, String authorizedEmail) {
        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email");
        } else {
            Log.d("Adding authorized user");
            addAuthorizedUser(profile, authorizedEmail);
        }
    }

    @Override
    public void deleteAuthorizedUser(String profileEmail, String authorizedEmail) {
        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email");
        } else {
            Log.d("Delete authorized user");
            deleteAuthorizedUser(profile, authorizedEmail);
        }
    }

    private void deleteEvent(UserProfile profile, Event eventTitle) {
        customProfileRepo.deleteEventForProfile(profile, eventTitle);
    }

    private void registerNewProfile(String profileEmail, String deviceToken) {
        ArrayList<Event> eventList = new ArrayList<>();
        UserProfile profile = new UserProfile(profileEmail, eventList);
        profile.setDeviceToken(deviceToken);

        profileRepository.insert(profile);
    }

    private void updateDeviceToken(UserProfile profile, String deviceToken) {
        customProfileRepo.updateProfileWithDeviceToken(profile, deviceToken);
    }

    private void createProfileWithEvent(String profileEmail, Event event) {
        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);

        UserProfile profile = new UserProfile(profileEmail, eventList);
        profileRepository.insert(profile);
    }

    private void addEventToProfile(UserProfile profile, Event event) {
        customProfileRepo.updateProfileWithEvent(profile, event);
    }

    private void addAuthorizedUser(UserProfile profile, String authorizedUser) {
        customProfileRepo.updateProfileWithAuthorizedEmail(profile, authorizedUser);
    }

    private void deleteAuthorizedUser(UserProfile profile, String authorizedUser) {
        customProfileRepo.deleteAuthorizedUser(profile, authorizedUser);
    }
}
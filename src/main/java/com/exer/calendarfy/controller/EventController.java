package com.exer.calendarfy.controller;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.UserProfile;
import com.exer.calendarfy.log.Log;
import com.exer.calendarfy.profile.ProfileCrud;
import com.exer.calendarfy.push.FCMPush;
import com.exer.calendarfy.push.PushRequest;
import com.exer.calendarfy.response.BaseResponse;
import com.exer.calendarfy.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class EventController {

    @Autowired
    ProfileCrud profileCrud;

    @Autowired
    FCMPush fcmPush;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getEventsForProfile")
    public ResponseEntity<ArrayList<Event>> getEventsForProfile(
            @RequestHeader(value = "profileEmail") String profileEmail
    ) {
        Log.d("Getting events for profile: " + profileEmail);

        UserProfile profile = profileCrud.getProfileByEmail(profileEmail);

        if (profile == null) {
            Log.d("No profile found for given email: " + profileEmail);
            return null;
        }

        return ResponseEntity.status(HttpStatus.OK).body(profile.getProfileEvents());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addEventForProfile")
    public ResponseEntity<HashMap<String, String>> addEventForProfile(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "eventTitle") String eventTitle,
            @RequestHeader(value = "eventDesc") String eventDesc,
            @RequestHeader(value = "sendPush", required = false) Boolean shouldSendPush
    ) {
        Log.d("Adding event: " + eventTitle + " for profile : " + profileEmail);
        BaseResponse response = new Response();
        Event event = new Event(eventTitle, eventDesc);

        profileCrud.addEventToProfile(profileEmail, event);

        if (shouldSendPush != null && shouldSendPush) {
            UserProfile profile = profileCrud.getProfileByEmail(profileEmail);

            if (profile != null && profile.getDeviceToken() != null) {
                PushRequest request = new PushRequest(profile.getDeviceToken(), event);
                fcmPush.sendPushToSender(request);
            } else {
                Log.d("Unable to send push as profile does not exist or device token is null");
            }
        }

        response.setIsSuccessful(true);
        return ResponseEntity.status(HttpStatus.OK).body(response.getResponse());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/deleteEventForProfile")
    public ResponseEntity<HashMap<String, String>> deleteEventForProfile(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "eventTitle") String eventTitle,
            @RequestHeader(value = "eventDesc") String eventDesc
    ) {
        Log.d("Deleting event: " + eventTitle + " for profile: " + profileEmail);

        BaseResponse response = new Response();
        Event event = new Event(eventTitle, eventDesc);

        profileCrud.deleteEventForProfile(profileEmail, event);

        response.setIsSuccessful(true);
        return ResponseEntity.status(HttpStatus.OK).body(response.getResponse());
    }
}

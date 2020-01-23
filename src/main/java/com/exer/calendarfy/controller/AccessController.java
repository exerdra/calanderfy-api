package com.exer.calendarfy.controller;

import com.exer.calendarfy.dao.profile.ProfileRepository;
import com.exer.calendarfy.model.UserProfile;
import com.exer.calendarfy.log.Log;
import com.exer.calendarfy.profile.ProfileCrud;
import com.exer.calendarfy.response.BaseResponse;
import com.exer.calendarfy.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AccessController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProfileCrud profileCrud;

    @GetMapping("/getAuthorizedUsers")
    public ResponseEntity<ArrayList<String>> getAuthorizedUsers (
            @RequestHeader(value = "profileEmail") String profileEmail
    ) {
        Log.d("Getting Authorized Users for profile: " + profileEmail);

        UserProfile profile = profileRepository.findFirstByProfileEmail(profileEmail);
        return ResponseEntity.status(HttpStatus.OK).body(profile.getAuthorizedUsers());
    }

    @PostMapping("/requestAccess")
    public ResponseEntity<HashMap<String, String>> requestAccess(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "requestedUser") String requestedUser
    ) {
        Log.d("Profile " + profileEmail + " is requesting access to " + requestedUser);
        BaseResponse response = new Response();
        response.setIsSuccessful(false);
        response.addResponseHeader("error", "This feature has not been implemented");

        return ResponseEntity.status(HttpStatus.OK).body(response.getResponse());
    }

    @PostMapping("/grantAccess")
    public ResponseEntity<HashMap<String, String>> grantAccess(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "requestedUser") String requestedUser
    ) {
        BaseResponse response = new Response();
        response.setIsSuccessful(true);

        profileCrud.addAuthorizedUserForProfile(profileEmail, requestedUser);

        return ResponseEntity.status(HttpStatus.OK).body(response.getResponse());
    }

    @PostMapping("/revokeAccess")
    public ResponseEntity<HashMap<String, String>> revokeAccess(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "requestedUser") String requestedUser
    ) {
        BaseResponse response = new Response();
        response.setIsSuccessful(true);

        profileCrud.deleteAuthorizedUser(profileEmail, requestedUser);

        return ResponseEntity.status(HttpStatus.OK).body(response.getResponse());
    }
}

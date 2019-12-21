package com.exer.calendarfy.controller;

import com.exer.calendarfy.data.Event;
import com.exer.calendarfy.data.UserProfile;
import com.exer.calendarfy.profile.ProfileCrud;
import com.exer.calendarfy.response.BaseResponse;
import com.exer.calendarfy.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CalanderfyController {

    @Autowired
    ProfileCrud profileCrud;

    @RequestMapping("healthcheck")
    public HashMap<String, String> healthcheck() {
        BaseResponse response = new Response();
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();

        response.addResponseHeader("uptime", String.valueOf(mxBean.getUptime()));
        response.setIsSuccessful(true);

        return response.getResponse();
    }

    @RequestMapping("getEventsForProfile")
    public ArrayList<Event> getEventsForProfile(@RequestHeader(value = "profileEmail") String profileEmail) {
        BaseResponse response = new Response();

        UserProfile profile = profileCrud.getProfileByEmail(profileEmail);

        return profile.getProfileEvents();
    }

    @RequestMapping("addEventForProfile")
    public HashMap<String, String> addEventForProfile(
            @RequestHeader(value = "profileEmail") String profileEmail,
            @RequestHeader(value = "eventTitle") String eventTitle,
            @RequestHeader(value = "eventDesc") String eventDesc
    ) {
        BaseResponse response = new Response();

        profileCrud.addEventToProfile(profileEmail, new Event(eventTitle, eventDesc));

        response.setIsSuccessful(true);
        return response.getResponse();
    }
}

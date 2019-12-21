package com.exer.calendarfy.dao;

import com.exer.calendarfy.data.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<UserProfile, String> {
    UserProfile findFirstByProfileEmail(String profileEmail);
}

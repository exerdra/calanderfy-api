package com.exer.calendarfy.dao.profile;

import com.exer.calendarfy.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<UserProfile, String> {
    UserProfile findFirstByProfileEmail(String profileEmail);
}

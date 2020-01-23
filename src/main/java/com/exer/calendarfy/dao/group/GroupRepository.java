package com.exer.calendarfy.dao.group;

import com.exer.calendarfy.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    Group findFirstByGroupName(String groupName);
}

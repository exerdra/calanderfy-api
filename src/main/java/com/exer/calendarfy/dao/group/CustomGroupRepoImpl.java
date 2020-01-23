package com.exer.calendarfy.dao.group;

import com.exer.calendarfy.model.Event;
import com.exer.calendarfy.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomGroupRepoImpl implements CustomGroupRepo {

    @Autowired
    MongoTemplate template;

    @Override
    public void updateGroupWithEvent(Group group, Event event) {
        Query query = new Query(Criteria.where("groupName").is(group.getGroupName()));
        Update update = new Update();
        ArrayList<Event> updatedEventList = group.getGroupEvents();
        updatedEventList.add(event);
        update.set("groupEvents", updatedEventList);

        template.updateFirst(query, update, Group.class);
    }

    @Override
    public void removeEventFromGroup(Group group, Event event) {
        Query query = new Query(Criteria.where("groupName").is(group.getGroupName()));
        Update update = new Update();
        ArrayList<Event> updatedEventList = group.getGroupEvents();
        updatedEventList.removeIf(e -> e.getEventTitle().equals(event.getEventTitle()));

        update.set("groupEvents", updatedEventList);

        template.updateFirst(query, update, Group.class);
    }

    @Override
    public void addUserToGroup(Group group, String profileEmail) {
        Query query = new Query(Criteria.where("groupName").is(group.getGroupName()));
        Update update = new Update();
        ArrayList<String> updatedEventList = group.getGroupUsers();
        updatedEventList.add(profileEmail);
        update.set("groupUsers", updatedEventList);

        template.updateFirst(query, update, Group.class);
    }

    @Override
    public void removeUserFromGroup(Group group, String profileEmail) {
        Query query = new Query(Criteria.where("groupName").is(group.getGroupName()));
        Update update = new Update();
        ArrayList<String> updatedEventList = group.getGroupUsers();
        updatedEventList.remove(profileEmail);
        update.set("groupUsers", updatedEventList);

        template.updateFirst(query, update, Group.class);
    }
}

package com.VinitGangurde.journalApp.repository;


import com.VinitGangurde.journalApp.entity.JournalEntry;
import com.VinitGangurde.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends  MongoRepository<User, ObjectId>{

    User findByuserName(String username);
}

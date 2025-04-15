package com.VinitGangurde.journalApp.service;

import com.VinitGangurde.journalApp.entity.JournalEntry;
import com.VinitGangurde.journalApp.entity.User;
import com.VinitGangurde.journalApp.repository.JournalEntryRepository;
import com.VinitGangurde.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void SaveEntry(User user){
        userRepository.save(user);

    }

    public List<User> getAlll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId ID){
        return userRepository.findById(ID);
    }

    public void deleteById(ObjectId ID){
        userRepository.deleteById(ID);
    }

    public User findByUserName(String userName){
        return userRepository.findByuserName(userName);
    }
}

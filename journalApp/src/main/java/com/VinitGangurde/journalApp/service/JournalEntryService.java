package com.VinitGangurde.journalApp.service;

import com.VinitGangurde.journalApp.entity.JournalEntry;
import com.VinitGangurde.journalApp.entity.User;
import com.VinitGangurde.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

   @Transactional
    public void SaveEntry(JournalEntry journalEn, String userName){

        try {
            User user=userService.findByUserName(userName);
            journalEn.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEn);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);

       } catch (Exception e) {
            System.out.println(e);
           throw new RuntimeException("An error accoured while saving the entry," , e);
        }



    }
    public void SaveEntry(JournalEntry journalEn){


        journalEntryRepository.save(journalEn);


    }

    public List<JournalEntry> getAlll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId ID){
        return journalEntryRepository.findById(ID);
    }

    @Transactional
    public Boolean deleteById(ObjectId ID, String userName){
         Boolean removed=false;
       try {

           User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(ID));

           if (removed) {
               userService.saveUser(user);
               journalEntryRepository.deleteById(ID);
           }
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An error occurred while deleting the entry.",e);
       }
       return removed;
    }
}

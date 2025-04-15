package com.VinitGangurde.journalApp.controler;


import com.VinitGangurde.journalApp.entity.JournalEntry;
import com.VinitGangurde.journalApp.entity.User;
import com.VinitGangurde.journalApp.service.JournalEntryService;
import com.VinitGangurde.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalEntryControlerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){


        User user = userService.findByUserName(userName);
         List<JournalEntry> all=user.getJournalEntries();

         if(all !=null && !all.isEmpty()){

             return new ResponseEntity<>(all,HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry,@PathVariable String userName){

        try {

            journalEntryService.SaveEntry(myentry,userName);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){

            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);

            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{userName}/{myId}")
    //here ? is known as wild card entity using this we can return any type of object
    public ResponseEntity<?> DeleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){

         journalEntryService.deleteById(myId,userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,
                                                    @RequestBody JournalEntry newEntry,
                                                    @PathVariable String userName){

        JournalEntry old=journalEntryService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());

            journalEntryService.SaveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);

        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}

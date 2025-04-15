package com.VinitGangurde.journalApp.controler;



import com.VinitGangurde.journalApp.entity.User;
import com.VinitGangurde.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAlll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.SaveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> udateUser(@RequestBody User user,@PathVariable String userName){
        User userInDb=userService.findByUserName(userName);

        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.SaveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

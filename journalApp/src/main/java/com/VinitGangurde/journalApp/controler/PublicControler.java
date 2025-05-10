package com.VinitGangurde.journalApp.controler;


import com.VinitGangurde.journalApp.entity.User;
import com.VinitGangurde.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicControler {


    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthcheck(){

        return "ok madhi aahe sagale";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.SaveNewUser(user);
    }


}

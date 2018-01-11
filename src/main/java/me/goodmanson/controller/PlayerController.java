package me.goodmanson.controller;

import me.goodmanson.orm.User;
import me.goodmanson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@RestController
@RequestMapping(path = "user")
public class PlayerController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }
}

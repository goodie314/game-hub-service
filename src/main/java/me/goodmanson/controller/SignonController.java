package me.goodmanson.controller;

import me.goodmanson.orm.User;
import me.goodmanson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignonController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "signon", method = RequestMethod.POST)
    public User signon(@RequestBody User user) {

        return this.userRepository.getUser("matt");
    }
}

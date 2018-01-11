package me.goodmanson.controller;

import me.goodmanson.orm.User;
import me.goodmanson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "")
    public String test() {
        User user = userRepository.getUser("matt");
        if (user != null) {
            return user.getUserName();
        }
        return "no user found named matt";
    }

    @RequestMapping(path = "{username}")
    public void addUser(@PathVariable String username) {
        User user = new User();
        user.setUserName(username);
        this.userRepository.addUser(user);
    }
}

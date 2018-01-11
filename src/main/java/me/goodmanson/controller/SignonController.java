package me.goodmanson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.goodmanson.orm.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignonController {

    @RequestMapping(path = "signon")
    public User signon() {
        return new User();
    }
}

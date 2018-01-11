package me.goodmanson.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.goodmanson.orm.User;
import me.goodmanson.repository.UserRepository;
import me.goodmanson.service.SignonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SignonController {

    @Autowired
    private SignonService signonService;

    @RequestMapping(path = "sign-in", method = RequestMethod.POST)
    public User signIn(@RequestBody String user) throws IOException {
        ObjectMapper mapper;
        JsonNode jsonObject;
        String userName;
        String password;

        mapper = new ObjectMapper();
        jsonObject = mapper.readTree(user);
        userName = jsonObject.get("userName").asText();
        password = jsonObject.get("password").asText();

        return this.signonService.signIn(userName, password);
    }

    @RequestMapping(path = "sign-up", method = RequestMethod.POST)
    public User signUp(@RequestBody String user) throws IOException {
        ObjectMapper mapper;
        JsonNode jsonObject;
        String userName;
        String password;

        mapper = new ObjectMapper();
        jsonObject = mapper.readTree(user);
        userName = jsonObject.get("userName").asText();
        password = jsonObject.get("password").asText();

        return this.signonService.signUp(userName, password);
    }
}

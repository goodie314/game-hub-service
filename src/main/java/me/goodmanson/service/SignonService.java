package me.goodmanson.service;

import me.goodmanson.orm.User;
import me.goodmanson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Service
public class SignonService {

    @Autowired
    private UserRepository userRepository;

    public User signIn(String userName, String password) {
        User validUser;

        validUser = this.userRepository.getUser(userName);
        if (validUser != null && validUser.getPassword().equals(password.hashCode())) {
            return validUser;
        }

        return null;
    }

    public User signUp(String userName, String password) {
        User user;

        user = new User();
        user.setUserName(userName);
        user.setPassword(password.hashCode());
        this.userRepository.addUser(user);
        return user;
    }
}

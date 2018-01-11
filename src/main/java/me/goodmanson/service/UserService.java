package me.goodmanson.service;

import me.goodmanson.orm.User;
import me.goodmanson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }
}

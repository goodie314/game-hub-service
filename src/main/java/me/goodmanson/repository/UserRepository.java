package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserRepository {

    @Autowired
    private Database database;

    private static final String userTable = "users";
    private Map<String, User> users;

    private void initData() {
        this.users = (Map<String, User>) this.database.initData(userTable);
    }

    // adds a user to the database
    public void addUser (User user) {
        this.initData();

        user.setUserName(user.getUserName().toLowerCase());
        this.users.put(user.getUserName(), user);
        try {
            this.database.addData(userTable, this.users);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns user with userName
    public User getUser(String userName) {
        this.initData();

        return this.users.get(userName.toLowerCase());
    }

    // returns all users in database
    public List<User> getAllUsers() {
        this.initData();

        return new ArrayList<>(this.users.values());
    }
}

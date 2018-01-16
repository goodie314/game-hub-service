package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserRepository {

    @Autowired
    private Database database;

    private static final String userTable = "users";
    private Map<String, User> users;

    private void initData() {
//        if (this.users != null) {
//            return;
//        }
//        try {
//            this.users = (Map<String, User>) this.database.getTable(userTable);
//            if (this.users == null) {
//                this.users = new HashMap<>();
//                this.database.addData(userTable, this.users);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        this.users = (Map<String, User>) this.database.initData(userTable);
    }

    public void addUser (User user) {
        this.initData();

        this.users.put(user.getUserName(), user);
        try {
            this.database.addData(userTable, this.users);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String userName) {
        this.initData();

        return this.users.get(userName);
    }

    public List<User> getUsers(List<String> userNames) {
        this.initData();

        return userNames.stream()
                .map(this.users::get)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        this.initData();

        return new ArrayList<>(this.users.values());
    }
}

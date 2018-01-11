package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepository {

    @Autowired
    private Database database;

    private static final String userTable = "users";
    private Map<String, User> users;

    public UserRepository () {
    }

    public void initData() {
        try {
            this.users = (Map<String, User>) this.database.getTable(userTable);
            if (this.users == null) {
                this.users = new HashMap<>();
                this.database.addData(userTable, this.users);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser (User user) {
        if (this.users == null) {
            this.initData();
        }

        this.users.put(user.getUserName(), user);
    }

    public User getUser(String userName) {
        if (this.users == null) {
            this.initData();
        }

        return this.users.get(userName);
    }
}

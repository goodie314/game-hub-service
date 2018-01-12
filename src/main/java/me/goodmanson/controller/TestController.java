//package me.goodmanson.controller;
//
//import me.goodmanson.orm.User;
//import me.goodmanson.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TestController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping(path = "")
//    public User test() {
//        return this.userRepository.getUser("matt");
//    }
//
//    @RequestMapping(path = "{username}")
//    public void addUser(@PathVariable String username) {
//        User user = new User();
//        user.setUserName(username);
//        this.userRepository.addUser(user);
//    }
//}

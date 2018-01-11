package me.goodmanson.orm;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Component
public class AvailableGame {

    private String game;
    private User requester;
    private Map<User, Boolean> invitees;
}

package me.goodmanson.orm;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Component
public class GameRequest {

    private String game;
    private User requester;
    private Map<User, Boolean> invitees;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Map<User, Boolean> getInvitees() {
        return invitees;
    }

    public void setInvitees(Map<User, Boolean> invitees) {
        this.invitees = invitees;
    }
}

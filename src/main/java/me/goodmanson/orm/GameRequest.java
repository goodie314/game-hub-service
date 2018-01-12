package me.goodmanson.orm;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Component
public class GameRequest implements Serializable {

    private String game;
    private User requester;
    private Map<String, Boolean> invitees;

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

    public Map<String, Boolean> getInvitees() {
        return invitees;
    }

    public void setInvitees(Map<String, Boolean> invitees) {
        this.invitees = invitees;
    }
}

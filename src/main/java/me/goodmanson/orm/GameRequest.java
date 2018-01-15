package me.goodmanson.orm;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Component
public class GameRequest implements Serializable {

    private Integer gameRequestId;
    private String game;
    private String requester;
    private List<String> invitees;
    private Date creationDate;
    private Integer numberAccepted;


    public Integer getGameRequestId() {
        return gameRequestId;
    }

    public void setGameRequestId(Integer gameRequestId) {
        this.gameRequestId = gameRequestId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public List<String> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<String> invitees) {
        this.invitees = invitees;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberAccepted() {
        return numberAccepted;
    }

    public void setNumberAccepted(Integer numberAccepted) {
        this.numberAccepted = numberAccepted;
    }

    public boolean equals(GameRequest request) {
        return this.creationDate.equals(request.creationDate);
    }
}

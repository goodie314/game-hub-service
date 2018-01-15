package me.goodmanson.orm;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Component
public class Game implements Serializable {

    private Integer gameId;
    private List<String> players;
    private String currentGameState;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(String currentGameState) {
        this.currentGameState = currentGameState;
    }
}

package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.Game;
import me.goodmanson.orm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Service
public class GameRepository {

    @Autowired
    private Database database;

    private static final String gamesTable = "games";
    private Map<Integer, Game> games;
    private Integer gameId;

    private void initData() {
//        if (this.games != null) {
//            return;
//        }
//        try {
//            this.games = (Map<Integer, Game>) this.database.getTable(gamesTable);
//            if (this.games== null) {
//                this.games = new HashMap<>();
//                this.database.addData(gamesTable, this.games);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        this.games = (Map<Integer, Game>) this.database.initData(gamesTable);
    }

//    private Integer getNextId() {
//        Optional<Integer> id;
//
//        if (this.gameId != null) {
//            return this.gameId++;
//        }
//
//        this.initData();
//
//        id = this.games.keySet()
//                .stream()
//                .max(Integer::compareTo);
//        this.gameId = id.orElse(0);
//
//        return this.gameId++;
//    }

    public Game getGame(Integer gameId) {
        this.initData();

        return this.games.get(gameId);
    }

    public List<Game> getGamesByUser(String userName) {
        this.initData();

        return this.games
                .values()
                .stream()
                .filter((game) -> game.getPlayers().contains(userName))
                .collect(Collectors.toList());
    }

    public void updateGame(Game game) {
        Game prevGame;
        this.initData();

        prevGame = this.getGame(game.getGameId());
        game.setPlayers(prevGame.getPlayers());
        this.games.put(game.getGameId(), game);
        try {
            this.database.addData(gamesTable, this.games);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createGame(Game game) {
        Integer gameId;
        this.initData();

        gameId = this.database.getNextKey(gamesTable, this.games.keySet());
        game.setGameId(gameId);
        this.games.put(gameId, game);
        try {
            this.database.addData(gamesTable, this.games);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGame(Integer gameId) {
        this.initData();

        this.games.remove(gameId);
        try {
            this.database.addData(gamesTable, this.games);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

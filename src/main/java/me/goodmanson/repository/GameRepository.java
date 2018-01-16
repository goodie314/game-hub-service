package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    private void initData() {
        this.games = (Map<Integer, Game>) this.database.initData(gamesTable);
    }

    // returns game with gameId
    public Game getGame(Integer gameId) {
        this.initData();

        return this.games.get(gameId);
    }

    // returns a list of games in which the userName is a player
    public List<Game> getGamesByUser(String userName) {
        this.initData();

        return this.games
                .values()
                .stream()
                .filter((game) -> game.getPlayers().contains(userName))
                .collect(Collectors.toList());
    }

    // updates game object in database
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

    // creates a game in the database
    public void createGame(Game game) {
        Integer gameId;
        this.initData();

        gameId = this.database.getNextKey(this.games.keySet());
        game.setGameId(gameId);
        this.games.put(gameId, game);
        try {
            this.database.addData(gamesTable, this.games);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // removes a game with gameId from the database
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

package me.goodmanson.service;

import me.goodmanson.orm.Game;
import me.goodmanson.orm.User;
import me.goodmanson.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void createGame(Game game) {
        game.setGameId(null);
        this.gameRepository.updateGame(game);
    }

    public void updateGame(Game game) {
        this.gameRepository.updateGame(game);
    }

    public Game getGame(Integer gameId) {
        return this.gameRepository.getGame(gameId);
    }

    public List<Game> getGamesByPlayer(String userName) {
        return this.gameRepository.getGamesByUser(userName);
    }

    public void deleteGame(Integer gameId) {
        this.gameRepository.deleteGame(gameId);
    }
}

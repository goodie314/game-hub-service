package me.goodmanson.controller;

import me.goodmanson.orm.Game;
import me.goodmanson.orm.User;
import me.goodmanson.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@RestController
@RequestMapping(path = "game")
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Game> getGamesByPlayer(@RequestParam String userName) {
        return this.gameService.getGamesByPlayer(userName);
    }

    @RequestMapping(path = "{gameId}", method = RequestMethod.GET)
    public Game getGame(@PathVariable Integer gameId) {
        return this.gameService.getGame(gameId);
    }

    @RequestMapping(path = "{gameId}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable Integer gameId) {
        this.gameService.deleteGame(gameId);
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public void createGame(@RequestParam Game game) {
        this.gameService.createGame(game);
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public void updateGame(@RequestBody Game game) {
        this.gameService.updateGame(game);
    }
}

package me.goodmanson.controller;

import me.goodmanson.orm.Game;
import me.goodmanson.orm.GameRequest;
import me.goodmanson.service.GameRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by u6062536 on 1/11/2018.
 */

@RestController
@RequestMapping(path = "game-request")
public class GameRequestController {

    @Autowired
    private GameRequestService gameRequestService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void makeGameRequest(@RequestBody GameRequest request) {
        this.gameRequestService.makeGameRequest(request);
    }

    @RequestMapping(path = "accept", method = RequestMethod.POST)
    public Game acceptRequest(@RequestBody GameRequest request) {
        return this.gameRequestService.acceptRequest(request);
    }

    @RequestMapping(path = "{game}/{userName}", method = RequestMethod.GET)
    public List<GameRequest> getGameRequests(@PathVariable String game,
                                             @PathVariable String userName) {
        return this.gameRequestService.getGameRequests(game, userName);
    }

    @RequestMapping(path = "decline/{gameRequestId}", method = RequestMethod.POST)
    public void declineGameRequest(@PathVariable Integer gameRequestId) {
        this.gameRequestService.declineRequest(gameRequestId);
    }
}

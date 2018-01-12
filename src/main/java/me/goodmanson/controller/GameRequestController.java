package me.goodmanson.controller;

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

    @RequestMapping(path = "{game}/{userName}", method = RequestMethod.GET)
    public List<GameRequest> getGameRequests(@PathVariable String game,
                                             @PathVariable List<String> userName) {
        return this.gameRequestService.getGameRequests(game, userName);
    }
}

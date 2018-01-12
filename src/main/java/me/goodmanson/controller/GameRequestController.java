package me.goodmanson.controller;

import me.goodmanson.orm.GameRequest;
import me.goodmanson.service.GameRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by u6062536 on 1/11/2018.
 */

@RestController
@RequestMapping(path = "game-request")
public class GameRequestController {

    @Autowired
    private GameRequestService gameRequestService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void getAvailableGames(@RequestBody GameRequest request) {
        this.gameRequestService.makeGameRequest(request);
    }
}

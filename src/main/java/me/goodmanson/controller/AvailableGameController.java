package me.goodmanson.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by u6062536 on 1/11/2018.
 */

@RestController
@RequestMapping(path = "available-game")
public class AvailableGameController {

    @RequestMapping(path = "{game}/{userId}")
    public void getAvailableGames(@PathVariable String game,
                                  @PathVariable Integer userId) {

    }
}

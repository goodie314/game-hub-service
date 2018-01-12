package me.goodmanson.service;

import me.goodmanson.orm.GameRequest;
import me.goodmanson.repository.GameRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRequestService {

    @Autowired
    private GameRequestRepository gameRequestRepository;

    public void makeGameRequest(GameRequest request) {
        this.gameRequestRepository.makeGameRequest(request);
    }

    public List<GameRequest> getGameRequests(String game, List<String> userName) {
        return this.gameRequestRepository.getGameRequests(game, userName);
    }
}

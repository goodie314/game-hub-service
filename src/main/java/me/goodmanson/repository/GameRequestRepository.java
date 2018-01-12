package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameRequestRepository {

    @Autowired
    private Database database;

    private static final String gameRequestsTable = "gameRequests";
    private Map<String, List<GameRequest>> gameRequests;

    private void initData() {
        if (this.gameRequests != null) {
            return;
        }
        try {
            this.gameRequests = (Map<String, List<GameRequest>>) this.database.getTable(gameRequestsTable);
            if (this.gameRequests == null) {
                this.gameRequests = new HashMap<>();
                this.database.addData(gameRequestsTable, this.gameRequests);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeGameRequest(GameRequest request) {
        List<GameRequest> requests;
        String game;
        this.initData();

        game = request.getGame();
        requests = this.gameRequests.get(game);
        if (requests == null) {
            requests = new ArrayList<>();
        }
        requests.add(request);
        this.gameRequests.put(game, requests);
        try {
            this.database.addData(gameRequestsTable, this.gameRequests);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GameRequest> getGameRequests(String game, List<String> userName) {
        List<GameRequest> requests;
        this.initData();

        requests = this.gameRequests.get(game);
        if (requests == null) {
            requests = new ArrayList<>();
        }

        return requests.stream()
                .filter(request -> request.getInvitees().equals(userName))
                .collect(Collectors.toList());
    }
}

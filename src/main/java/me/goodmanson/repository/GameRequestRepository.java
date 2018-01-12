package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.Game;
import me.goodmanson.orm.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameRequestRepository {

    @Autowired
    private Database database;

    private static final String gameRequestsTable = "gameRequests";
    private Map<String, GameRequest> gameRequests;

    private void initData() {
        if (this.gameRequests != null) {
            return;
        }
        try {
            this.gameRequests = (Map<String, GameRequest>) this.database.getTable(gameRequestsTable);
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
        this.initData();

        this.gameRequests.put(request.getGame(), request);
        try {
            this.database.addData(gameRequestsTable, this.gameRequests);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

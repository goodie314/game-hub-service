package me.goodmanson.repository;

import me.goodmanson.database.Database;
import me.goodmanson.orm.Game;
import me.goodmanson.orm.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameRequestRepository {

    @Autowired
    private Database database;

    @Autowired
    private GameRepository gameRepository;

    private static final String gameRequestsTable = "gameRequests";
    private Map<Integer, GameRequest> gameRequests;

    private void initData() {
        this.gameRequests = (Map<Integer, GameRequest>) this.database.initData(gameRequestsTable);
    }

    // adds a GameRequest to the database
    public void makeGameRequest(GameRequest request) {
        Integer id;
        this.initData();

        id = this.database.getNextKey(this.gameRequests.keySet());
        request.setGameRequestId(id);
        request.setNumberAccepted(0);
        this.gameRequests.put(id, request);
        try {
            this.database.addData(gameRequestsTable, this.gameRequests);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // gets all GameRequests for a game type game where the userName is an invitee in the GameRequest
    public List<GameRequest> getGameRequests(String game, String userName) {
        this.initData();

        return this.gameRequests.values().stream()
                .filter(request -> {
                    if (!request.getGame().equals(game)) {
                        return false;
                    }
                    for (String user : request.getInvitees()) {
                        if (user.equals(userName)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    // Accepts the game request. If all invitees have accepted it removes the request from the database and
    // create a new game which is returned
    public Game acceptRequest(GameRequest request) {
        Game game;
        Integer numberAccepted;
        List<String> players;
        this.initData();

        numberAccepted = request.getNumberAccepted();
        numberAccepted++;
        request.setNumberAccepted(numberAccepted);
        this.gameRequests.put(request.getGameRequestId(), request);

        if (numberAccepted.equals(request.getInvitees().size())) {
            game = new Game();
            players = request.getInvitees();
            players.add(request.getRequester());
            Collections.shuffle(players);
            game.setPlayers(players);
            game.setGameDescriptor(request.getGame());
            this.gameRepository.createGame(game);
            this.gameRequests.remove(request.getGameRequestId());
            try {
                this.database.addData(gameRequestsTable, this.gameRequests);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return game;
        }
        else {
            try {
                this.database.addData(gameRequestsTable, this.gameRequests);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // declines the request and removes it from the database
    public void declineRequest(Integer gameRequestId) {
        this.initData();

        this.gameRequests.remove(gameRequestId);
        try {
            this.database.addData(gameRequestsTable, this.gameRequests);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

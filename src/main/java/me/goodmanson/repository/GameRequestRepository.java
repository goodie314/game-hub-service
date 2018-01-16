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
    private Integer gameRequestId;

    private void initData() {
//        if (this.gameRequests != null) {
//            return;
//        }
//        try {
//            this.gameRequests = (Map<Integer, GameRequest>) this.database.getTable(gameRequestsTable);
//            if (this.gameRequests == null) {
//                this.gameRequests = new HashMap<>();
//                this.database.addData(gameRequestsTable, this.gameRequests);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        this.gameRequests = (Map<Integer, GameRequest>) this.database.initData(gameRequestsTable);
    }

//    private Integer getNextId() {
//        Optional<Integer> id;
//
//        if (this.gameRequestId != null) {
//            return this.gameRequestId++;
//        }
//
//        this.initData();
//
//        id = this.gameRequests.keySet()
//                .stream()
//                .max(Integer::compareTo);
//        this.gameRequestId = id.orElse(0);

//        return this.gameRequestId++;
//    }

    public void makeGameRequest(GameRequest request) {
//        List<GameRequest> requests;
//        String game;
        Integer id;
        this.initData();

//        game = request.getGame();
//        requests = this.gameRequests.get(game);
//        if (requests == null) {
//            requests = new ArrayList<>();
//        }
//        id = this.getNextId();
        id = this.database.getNextKey(gameRequestsTable, this.gameRequests.keySet());
        request.setGameRequestId(id);
        request.setNumberAccepted(0);
//        requests.add(request);
        this.gameRequests.put(id, request);
        try {
            this.database.addData(gameRequestsTable, this.gameRequests);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GameRequest> getGameRequests(String game, String userName) {
//        List<GameRequest> requests;
        this.initData();

//        requests = this.gameRequests.get(game);
//        if (requests == null) {
//            requests = new ArrayList<>();
//        }

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

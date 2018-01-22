package me.goodmanson.endpoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by U6062536 on 1/22/2018.
 */

//@Service
//@Scope(value = "singleton")
@ServerEndpoint(value = "/websocket/test")
public class TestEndpoint {

    private static Set<Session> remoteEndpoints = new HashSet<>();
    private static List<String> messages = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
//        RemoteEndpoint.Basic remoteEndpoint = session.getBasicRemote();
        remoteEndpoints.add(session);
        try {
            session.getBasicRemote().sendText(String.join("\n", messages));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        session.addMessageHandler(new TestMessageHandler(remoteEndpoint));
    }

    @OnMessage()
    public void onMessage(String message, Session session) {
//        remoteEndpoints.stream()
//                .filter(s -> !(s.getId().equals(session.getId())))
//                .forEach(endpoint -> {
//                    try {
//                        endpoint.getBasicRemote().sendText(message);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
        messages.add(session.getId() + message);
        message = String.join("\n", messages);
        for (Session s : remoteEndpoints) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        remoteEndpoints.remove(session);
    }

//    private static class TestMessageHandler implements MessageHandler.Whole<String> {
//
//        private final RemoteEndpoint.Basic remoteEndpoint;
//
//        private TestMessageHandler(RemoteEndpoint.Basic remoteEndpoint) {
//            this.remoteEndpoint = remoteEndpoint;
//        }
//
//        @Override
//        public void onMessage(String message) {
//            try {
//                if (remoteEndpoint != null) {
//                    remoteEndpoint.sendText(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

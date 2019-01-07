package academy.learnprogramming.websocket.programmatic;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyProgrammaticClient extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        try {
            session.getBasicRemote().sendText("Hello server from programmatic");
        } catch (IOException ex) {
            Logger.getLogger(MyProgrammaticClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        session.addMessageHandler((MessageHandler.Whole<String>) message -> {
            System.out.println("Client: " + message);
            try {
                session.getBasicRemote().sendText("In response to what was received from the server. This is the programmatic");
            } catch (IOException ex) {
                Logger.getLogger(MyProgrammaticClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}

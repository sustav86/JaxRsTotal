package academy.learnprogramming.websocket.programmatic;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyProgrammaticEndPoint extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {



        session.addMessageHandler((MessageHandler.Whole<String>) s -> {
            System.out.println("Server: " + s);
            try {
                session.getBasicRemote().sendText("In response to message received from programmatic. This is the server");
            } catch (IOException ex) {
                Logger.getLogger(MyProgrammaticEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }


}

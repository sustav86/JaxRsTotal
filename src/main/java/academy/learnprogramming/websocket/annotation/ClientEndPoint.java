package academy.learnprogramming.websocket.annotation;

import javax.websocket.ClientEndpoint;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint(decoders = MySimplePojoDecoder.class,
        encoders = MySimplePojoEncoder.class)
public class ClientEndPoint {

    @OnMessage
    public void processMessage(final Session session, MySimplePojo mySimplePojo) throws IOException, EncodeException {
        Logger logger = Logger.getLogger(ClientEndPoint.class.getName());

        logger.log(Level.INFO, "%%%%%%%%%%% My simple pojo received on the client %%%%%%%%%%%%");
        logger.log(Level.INFO, mySimplePojo.toString());
        session.getBasicRemote().sendObject(mySimplePojo);
    }
}
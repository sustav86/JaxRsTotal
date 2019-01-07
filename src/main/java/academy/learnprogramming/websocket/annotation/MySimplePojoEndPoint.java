package academy.learnprogramming.websocket.annotation;

import academy.learnprogramming.websocket.data.MySimplePojo;
import academy.learnprogramming.websocket.data.MySimplePojoDecoder;
import academy.learnprogramming.websocket.data.MySimplePojoEncoder;

import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/pojo", encoders = MySimplePojoEncoder.class,
        decoders = MySimplePojoDecoder.class)
public class MySimplePojoEndPoint {

    @Inject
    private Logger logger;


    @OnOpen
    public void opened(final Session session) throws IOException, EncodeException {
        MySimplePojo mySimplePojo = new MySimplePojo("Java EE", "bla@bla.com", "Great day! How is life?");
        session.getBasicRemote().sendObject(mySimplePojo);

    }

    @OnMessage
    public void processMessage(final Session session, MySimplePojo mySimplePojo) throws IOException, EncodeException {
        logger.log(Level.INFO, "My simple pojo received on the server *************");
        logger.log(Level.INFO, mySimplePojo.toString());
        session.getBasicRemote().sendObject(mySimplePojo);

    }


}
package academy.learnprogramming.config;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@Provider
public class JaxRsClientRequestFilter implements ClientRequestFilter {

    @Inject
    Logger logger;


    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {

        logger.log(Level.INFO,"Client request filter invoked" );

        //TODO implement method

    }
}

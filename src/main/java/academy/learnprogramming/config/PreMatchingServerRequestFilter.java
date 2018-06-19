package academy.learnprogramming.config;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@PreMatching
public class PreMatchingServerRequestFilter implements ContainerRequestFilter {

    @Inject
    Logger logger;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //X-Http-Method-Override == DELETE
        logger.log(Level.INFO, "Original http method was " + requestContext.getMethod());

        String httpMethod = requestContext.getHeaderString("X-Http-Method-Override");


        if (httpMethod != null && !httpMethod.isEmpty()) {
            logger.log(Level.INFO, "Http method " + httpMethod);

            requestContext.setMethod(httpMethod);

            logger.log(Level.INFO, "Altered http method now is " + requestContext.getMethod());

        }
    }
}

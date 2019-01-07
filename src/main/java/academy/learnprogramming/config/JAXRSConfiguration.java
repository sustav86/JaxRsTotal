package academy.learnprogramming.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api/v1") //https://foo.com/resources/payroll
public class JAXRSConfiguration extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        return super.getClasses();
//    }
}

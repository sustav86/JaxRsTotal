package academy.learnprogramming.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloResource {


    @Path("{name}")
    @GET
    public Response sayHello(@PathParam("name") String name) {
        String greeting = "Hello " + name;
        return Response.ok(greeting).build();
    }

    @GET
    @Path("greet") //api/v1/hello/greet - GET Method
    public String greet() {
        return "Hello, World";
    }
}

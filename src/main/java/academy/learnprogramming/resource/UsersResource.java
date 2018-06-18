package academy.learnprogramming.resource;

import academy.learnprogramming.entities.ApplicationUser;
import academy.learnprogramming.entities.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.UUID;

@Path("users")
@Consumes("application/json")
//@Produces("application/json")
public class UsersResource {


    @POST //api/v1/users/form
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("form")
    public Response createNewUser(@FormParam("email") String email, @FormParam("password") String password, @HeaderParam("Referer") String referer) {


        return null;
    }


    @POST
    @Path("map")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewUser(MultivaluedMap<String, String> formMap, @Context HttpHeaders httpHeaders) {

//        httpHeaders.getHeaderString("Referer");

        httpHeaders.getRequestHeader("Referer").get(0);

        for (String h : httpHeaders.getRequestHeaders().keySet()) {
            System.out.println("Header key set " + h);
        }

        String email = formMap.getFirst("email");
        String password = formMap.getFirst("password");

        return null;
    }

    @POST
    @Path("bean")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewUser(@BeanParam ApplicationUser applicationUser, @CookieParam("user") String user) {

        return null;
    }


    //    GET /api/v1/employees/employees HTTP/1.1
//    Host www.ourdomain.com
//    User-Agent: Java/1.8.0_151
//    Content-Type: text/plain;charset=utf-8
//    Accept: application/json;q=.7, application/xml
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserById(@PathParam("id") Long id) {


        return null;
    }


}





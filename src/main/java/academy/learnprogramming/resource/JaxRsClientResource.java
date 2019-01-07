package academy.learnprogramming.resource;

import javax.inject.Inject;
import javax.json.*;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("programmatic")
@Produces(MediaType.APPLICATION_JSON)
public class JaxRsClientResource {

    @Inject
    JaxRsClient jaxRsClient;


    @Path("breach/{email}")
    @GET
    public Response checkBreaches(@PathParam("email") @NotEmpty String email) {

        JsonArray breaches1 = jaxRsClient.getBreaches(email);

        List<JsonObject> jsonObjects = new ArrayList<>();

        JsonArray jsonArray = Json.createArrayBuilder().build();

        if (breaches1.size() > 0) {
            for (JsonValue jsonValue : breaches1) {
                JsonObject jsonObject = jsonValue.asJsonObject();

                jsonObjects.add(Json.createObjectBuilder().add("breach_domain", jsonObject.getString("Domain"))
                        .add("breach_date", jsonObject.getString("BreachDate")).build());

            }


            return Response.ok(jsonObjects).build();
        }
        return Response.ok("No breaches found for email " + email).build();
    }
}

package academy.learnprogramming.resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class JaxRsClient {

    private Client client;
    private final String haveIBeenPawned = "https://haveibeenpwned.com/api/v2/breachedaccount/";

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    private void destroy() {
        if (client != null) {

            //Be sure to close the client to prevent resource leakage

            client.close();
        }

    }

    public int checkBreaches(String email) {
        JsonArray jsonValues = client.target(haveIBeenPawned + email).request(MediaType.TEXT_PLAIN).get(JsonArray.class);


            for (JsonValue jsonValue : jsonValues) {

                JsonObject jsonObject = jsonValue.asJsonObject();

                String domain = jsonObject.getString("Domain");
                String breachDate = jsonObject.getString("BreachDate");

                System.out.println("Breach name is " + domain);
                System.out.println("Breach date is " + breachDate);


                System.out.println();

            }



        return jsonValues.size();
    }

}

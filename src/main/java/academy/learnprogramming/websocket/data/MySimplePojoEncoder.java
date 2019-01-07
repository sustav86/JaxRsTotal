package academy.learnprogramming.websocket.data;

import javax.json.bind.JsonbBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MySimplePojoEncoder implements Encoder.Text<MySimplePojo> {

    @Override
    public String encode(MySimplePojo mySimplePojo) throws EncodeException {
        //Using JSON-B (JSR 367) API for mapping to JSON from T
        return JsonbBuilder.create().toJson(mySimplePojo);

    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

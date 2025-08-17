package org.elainevalles.httpserver.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {

    private static ObjectMapper myObjectMapper = defaultObjectMapper();


    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    // Signals that an I/O exception of some sort has occurred
    public static JsonNode ParseJsonToJsonNode(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }
}

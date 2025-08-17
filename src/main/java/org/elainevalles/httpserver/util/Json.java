package org.elainevalles.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

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

    // Parse Json to POJO
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    // Parse POJO to Json
    public static JsonNode toJson(Object obj) throws JsonProcessingException {
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyFlag(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    // Generate json
    private static String generateJson(Object o, boolean flag) throws JsonProcessingException {
            ObjectWriter  objwriter = myObjectMapper.writer();
            if (flag) {
                objwriter = objwriter.with(SerializationFeature.INDENT_OUTPUT);
            }
            return objwriter.writeValueAsString(o);
    }
}

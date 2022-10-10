package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Json {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // handle UnrecognizedPropertyException : Unrecognized field "xxx"
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }


    public static <T> T fromJson(JsonNode node, Class<T> clazz) throws JsonProcessingException {

        return objectMapper.treeToValue(node, clazz);
    }


    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
    }

    public static <T> T readJsonFile(URL filePath, Class<T> clazz) throws IOException, URISyntaxException {
        T obj = objectMapper.readValue(Paths.get(filePath.toURI()).toFile(), clazz);
        return obj;
    }
}

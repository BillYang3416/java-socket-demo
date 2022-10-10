package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import model.Book;
import org.junit.jupiter.api.Test;
import utils.pojo.SimpleTestCaseJsonPOJO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"Coder From Scratch\",\n" +
            "  \"author\": \"James Harden\"\n" +
            "}";


    @Test
    void parse() throws JsonProcessingException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "Coder From Scratch");

    }


    @Test
    void fromJson() throws JsonProcessingException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        assertEquals(node.get("title").asText(), "Coder From Scratch");

    }

    @Test
    void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        System.out.println(node);
        assertEquals(node.get("title").asText(), "Testing 123");
    }

    @Test
    void stringify() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.stringify(node));
        System.out.println(Json.prettyPrint(node));
    }


    @Test
    void readJsonFile() throws IOException, URISyntaxException {
        // ref: https://stackoverflow.com/a/17351116
        URL res = getClass().getClassLoader().getResource("book.json");
        Book book = Json.readJsonFile(res, Book.class);
        System.out.println(book.getTitle());
        assertEquals(book.getTitle(),"Thinking in Java");
    }

}
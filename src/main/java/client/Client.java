package client;

import com.fasterxml.jackson.databind.JsonNode;
import model.Book;
import utils.Json;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Client {
    private static final int PORT = 7777;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {


        // ref: https://gist.github.com/chatton/14110d2550126b12c0254501dde73616
        try (Socket socket = new Socket(ADDRESS, PORT);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             // get the output stream from the socket.
             OutputStream outputStream = socket.getOutputStream();
             // create an object output stream from the output stream so we can send an object through it
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ) {
            // 1. read json file
            URL res = Client.class.getClassLoader().getResource("book.json");
            Book book = Json.readJsonFile(res, Book.class);

            // 2. send java object
            objectOutputStream.writeObject(book);

            String receivedResponse = inputStream.readUTF();
            System.out.println(receivedResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }


}

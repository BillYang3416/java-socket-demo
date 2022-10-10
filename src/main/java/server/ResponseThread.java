package server;

import model.Book;

import java.io.*;
import java.net.Socket;

public class ResponseThread extends Thread {

    private final Socket socket;

    public ResponseThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        // try-with-resource
        try (
                // get the input stream from the connected socket
                InputStream inputStream = socket.getInputStream();
                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());) {

            Book book = (Book) objectInputStream.readObject();
            outputStream.writeUTF("The book " + book.toString() + " was sent to the server!");
            System.out.println("The book " + book.toString()  + " was sent to the server!");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

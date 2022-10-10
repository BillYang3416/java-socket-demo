package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 7777;
    private static final String ADDRESS = "localhost";

    public static void main(String[] args) {
        // try-with-resource
        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
            ) {
            // listens and accepts new connections from the clients keeping loop forever
            while (true) {
                ResponseThread response = new ResponseThread(serverSocket.accept());
                response.start(); // starts respond to the client on another thread
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}

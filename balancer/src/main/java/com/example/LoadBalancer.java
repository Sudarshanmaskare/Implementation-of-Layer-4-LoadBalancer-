package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class LoadBalancer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("LoadBalancer Started at port : " + 8081);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("TCP Connection extablished with client : " + socket.toString());
            handleSocket(socket);

        }
    }

    private static void handleSocket(Socket socket) {
        clientScoketHandler clientScoketHandler = new clientScoketHandler(socket);
        clientScoketHandler.run();
    }
}

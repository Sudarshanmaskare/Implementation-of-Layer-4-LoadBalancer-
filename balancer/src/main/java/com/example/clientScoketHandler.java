package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.utils.BackendServers;

public class clientScoketHandler {

    private Socket clientSocket;

    public clientScoketHandler(final Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            InputStream clientToLoadBalancerInputStream = clientSocket.getInputStream();
            OutputStream loadBalancerToClientOutputStream = clientSocket.getOutputStream();

            String backendHost = BackendServers.getHost();
            System.out.println("Host Selected to handle this request " + backendHost);

            Socket backendSocket = new Socket(backendHost, 8080);

            InputStream backendServerToLoadBalancerIS = backendSocket.getInputStream();
            OutputStream loadBalancerToBackendServerOS = backendSocket.getOutputStream();

            try {
                int data;
                while ((data = clientToLoadBalancerInputStream.read()) != -1) {
                    loadBalancerToBackendServerOS.write(data);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                int data;

                while ((data = backendServerToLoadBalancerIS.read()) != -1) {
                    loadBalancerToClientOutputStream.write(data);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (

        IOException e) {
            throw new RuntimeException();
        }
    }
}
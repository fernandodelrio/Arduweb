package com.arduweb.server;

import com.arduweb.parsing.JSONParser;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket providerSocket;
    private Socket connection = null;
    private ObjectOutputStream out;
    private Fernanduino fernanduino;
    public String message;

    public Server(Fernanduino fernanduino) {
        this.fernanduino = fernanduino;
    }

    public void run() {
        try {
            providerSocket = new ServerSocket(2004, 10);
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            sendMessage(JSONParser.toJSON(fernanduino.joystick));
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            close();
        }
    }

    private void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void close() {
        try {
            out.close();
            providerSocket.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

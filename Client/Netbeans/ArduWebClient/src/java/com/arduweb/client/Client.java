package com.arduweb.client;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {

    private Socket requestSocket;
    private ObjectInputStream objectInputStream;
    private String message;

    public void run() {
        try {
            requestSocket = new Socket("localhost", 2004);
            objectInputStream = new ObjectInputStream(requestSocket.getInputStream());
            try {
                message = (String) objectInputStream.readObject();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            close();
        }
    }

    private void close() {
        try {
            objectInputStream.close();
            requestSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getMessage() {
        return message;
    }
}

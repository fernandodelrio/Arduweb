package com.arduweb.server;

public class MainApplication {

    public static void main(String[] args) throws InterruptedException {
        Fernanduino fernanduino = new Fernanduino("COM18");
        if (fernanduino.initialize()) {
            Server server = new Server(fernanduino);
            while (true) {
                server.run();
            }
        }
    }
}

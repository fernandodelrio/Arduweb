package com.arduweb.server;

import com.arduweb.hardware.Joystick;
import gnu.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public final class Fernanduino implements SerialPortEventListener {

    private SerialPort serialPort;
    private String portName;
    private String appName;
    private BufferedReader input;
    private static final int TIME_OUT = 1000; // Port open timeout
    private static final int DATA_RATE = 9600; // Arduino serial port
    public Joystick joystick;

    public Fernanduino(String portName) {
        appName = getClass().getName();
        joystick = new Joystick();
        this.portName = portName;
    }

    public boolean initialize() {
        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
            // Enumerate system ports and try connecting to Arduino over each
            while (portId == null && portEnum.hasMoreElements()) {
                // Iterate through your host computer's serial port IDs
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                if (currPortId.getName().equals(portName)
                        || currPortId.getName().startsWith(portName)) {
                    // Open serial port and try to connect to the Arduino on this port
                    serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                    portId = currPortId;
                    System.out.println("Connected on port" + currPortId.getName());
                    break;
                }
            }
            if (portId == null || serialPort == null) {
                System.out.println("Could not connect to Arduino");
                return false;
            }
            // Set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            // Add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            // Give the Arduino some time
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            return true;
        } catch (PortInUseException | UnsupportedCommOperationException | TooManyListenersException e) {
            System.out.println("Error : " + e.getMessage());
        }
        return false;
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent event) {
        try {
            switch (event.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (input == null) {
                        input = new BufferedReader(
                                new InputStreamReader(
                                serialPort.getInputStream()));
                    }
                    String inputLine = input.readLine();
                    String[] values = inputLine.split(";");
                    joystick.setAButtonStatus(Integer.parseInt(values[0]));
                    joystick.setBButtonStatus(Integer.parseInt(values[1]));
                    joystick.setCButtonStatus(Integer.parseInt(values[2]));
                    joystick.setDButtonStatus(Integer.parseInt(values[3]));
                    joystick.setStickButtonStatus(Integer.parseInt(values[4]));
                    joystick.setXAxisStatus(Integer.parseInt(values[5]));
                    joystick.setYAxisStatus(Integer.parseInt(values[6]));
                    if (joystick.getStickButtonStatus() == 0) {
                        close();
                    }
                    break;

                default:
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            System.out.println("Closing serial connection...");
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
}

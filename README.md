Arduweb
=======

A project to create an arduino + joystick + smartphone integration.

This project uses these components:

1. Arduino Uno Rev 3: http://arduino.cc/en/Main/arduinoBoardUno
2. Joystick Shield: http://www.dfrobot.com/wiki/index.php/Input_Shield_For_Arduino_SKU:_DFR0008

After upload the sketch, the arduino sends the status of the buttons, everytime they change. 
Then a java server application listen the serial port to read the status of the buttons. This application creates a socket connection and waits a client application connect to send these values.
The client application display the data using JSON format in a Web Page.
Finally an android application keeps reading this Web Page to know the status of the buttons.

To use the project just:

1. Upload the Arduino sketch
2. Run the server application
3. Run the client application
4. Install the android app

Next step:

Create a next-gen experience, using a Wifi Shield or a Bluetooth Shield ;-)

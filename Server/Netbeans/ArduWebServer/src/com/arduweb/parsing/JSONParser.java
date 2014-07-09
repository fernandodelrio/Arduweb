package com.arduweb.parsing;

import com.arduweb.hardware.Joystick;
import com.google.gson.Gson;

public class JSONParser {

    public static String toJSON(Joystick g) {
        Gson gson = new Gson();
        String json = gson.toJson(g);
        return json;
    }

    public static Joystick toJoystick(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Joystick.class);
    }
}

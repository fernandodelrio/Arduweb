package com.arduweb.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.arduweb.app.objects.Square;
import com.arduweb.hardware.Joystick;
import com.arduweb.parsing.JSONParser;
import com.arduweb.parsing.WebParser;

public class AppView extends GenericView {

	private Square square;
	private Joystick joystick;

	public AppView(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		square = new Square(this, 200, 200, 300, 300, Color.GRAY);
	}

	@Override
	protected void _draw(Canvas canvas) {
		clearScreen(canvas);
		square.draw(canvas);
	}

	@Override
	protected void _update() {
		joystick = JSONParser.toJoystick(WebParser
				.getHTML("http://192.168.0.6:8080/ArduWebClient/"));
		if (joystick.getAButtonStatus() == 0) {
			square.setColor(Color.BLUE);
		} else if (joystick.getBButtonStatus() == 0) {
			square.setColor(Color.YELLOW);
		} else if (joystick.getCButtonStatus() == 0) {
			square.setColor(Color.RED);
		} else if (joystick.getDButtonStatus() == 0) {
			square.setColor(Color.GREEN);
		}
		if (joystick.getXAxisStatus() > 536) {
			square.setX(square.getX() - 10);
		} else if (joystick.getXAxisStatus() < 526) {
			square.setX(square.getX() + 10);
		}
		if (joystick.getYAxisStatus() > 527) {
			square.setY(square.getY() + 10);
		} else if (joystick.getYAxisStatus() < 517) {
			square.setY(square.getY() - 10);
		}
	}
}

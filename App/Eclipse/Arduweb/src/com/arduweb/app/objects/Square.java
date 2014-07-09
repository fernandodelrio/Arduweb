package com.arduweb.app.objects;

import android.graphics.Canvas;

import com.arduweb.app.views.GenericView;

public class Square {

	public Square(GenericView view, int x, int y, int width, int height,
			int color) {
		this.view = view;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	private GenericView view;
	private int x;
	private int y;
	private int width;
	private int height;
	private int color;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void draw(Canvas canvas) {
		view.drawRectangle(canvas, x, y, width, height, color);
	}
}

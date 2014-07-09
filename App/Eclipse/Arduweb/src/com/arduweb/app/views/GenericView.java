package com.arduweb.app.views;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class GenericView extends View {

	protected Context context = null;
	protected Paint paint = null;
	protected Random rand = null;
	protected BitmapFactory.Options options;
	protected int screenW;
	protected int screenH;
	protected float wRatio = 1f;
	protected float hRatio = 1f;
	protected int randNumber;
	protected int _height;
	protected int _width;
	protected int screenWidth = 1024;
	protected int screenHeight = 768;

	@SuppressWarnings("deprecation")
	protected GenericView(Context context) {
		super(context);
		this.context = context;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		screenW = display.getWidth();
		screenH = display.getHeight();
		wRatio = (float) screenW / (float) 1024;
		hRatio = (float) screenH / (float) 768;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		options = new BitmapFactory.Options();
		options.inScaled = false;
		rand = new Random();
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (_height == 0 && _width == 0) {
			_height = View.MeasureSpec.getSize(heightMeasureSpec);
			_width = View.MeasureSpec.getSize(widthMeasureSpec);
		}
		setMeasuredDimension(_width, _height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.scale(wRatio, hRatio);
		render(canvas);
		canvas.restore();
	}

	protected void init() {

	}

	private void render(Canvas canvas) {
		_draw(canvas);
		_update();
		invalidate();
	}

	protected void _draw(Canvas canvas) {
		clearScreen(canvas);
	}

	protected void _update() {

	}

	public void clearScreen(Canvas canvas) {
		drawRectangle(canvas, 0, 0, screenWidth, screenHeight, Color.WHITE);
	}

	public void drawLine(Canvas canvas, int x0, int y0, int x, int y) {
		canvas.drawLine(x0, y0, x, y, paint);
	}

	public void drawRectangle(Canvas canvas, int x, int y, int width,
			int height, int color) {
		paint.setColor(color);
		canvas.drawRect(x, y, x + width, y + height, paint);
	}

	public void drawText(Canvas canvas, String text, int x, int y, int size,
			Paint.Align algn, int alpha, int color) {
		paint.setARGB(alpha, 255, 255, 255);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setColor(color);
		paint.setTextAlign(algn);
		paint.setTextSize(size);
		canvas.drawText(text, x, y, paint);
	}

	public int generateRandom(int begin, int end) {
		return begin + rand.nextInt(end - begin + 1);
	}

	public void drawImage(Canvas canvas, Bitmap image, int x, int y) {
		canvas.drawBitmap(image, x, y, paint);
	}

	public void drawImagePart(Canvas canvas, Bitmap image, int cx, int cy,
			int cw, int ch, int x, int y, int w, int h) {
		canvas.drawBitmap(image, new Rect(cx, cy, cx + cw, cy + ch), new Rect(
				x, y, x + w, y + h), paint);
	}
}
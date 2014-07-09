package com.arduweb.hardware;

public class Joystick {

	private int aButtonStatus;
	private int bButtonStatus;
	private int cButtonStatus;
	private int dButtonStatus;
	private int stickButtonStatus;
	private int xAxisStatus;
	private int yAxisStatus;

	public Joystick() {
		this.aButtonStatus = -1;
		this.bButtonStatus = -1;
		this.cButtonStatus = -1;
		this.dButtonStatus = -1;
		this.stickButtonStatus = -1;
		this.xAxisStatus = -1;
		this.yAxisStatus = -1;
	}

	public int getAButtonStatus() {
		return aButtonStatus;
	}

	public void setAButtonStatus(int aButtonStatus) {
		this.aButtonStatus = aButtonStatus;
	}

	public int getBButtonStatus() {
		return bButtonStatus;
	}

	public void setBButtonStatus(int bButtonStatus) {
		this.bButtonStatus = bButtonStatus;
	}

	public int getCButtonStatus() {
		return cButtonStatus;
	}

	public void setCButtonStatus(int cButtonStatus) {
		this.cButtonStatus = cButtonStatus;
	}

	public int getDButtonStatus() {
		return dButtonStatus;
	}

	public void setDButtonStatus(int dButtonStatus) {
		this.dButtonStatus = dButtonStatus;
	}

	public int getStickButtonStatus() {
		return stickButtonStatus;
	}

	public void setStickButtonStatus(int stickButtonStatus) {
		this.stickButtonStatus = stickButtonStatus;
	}

	public int getXAxisStatus() {
		return xAxisStatus;
	}

	public void setXAxisStatus(int xAxisStatus) {
		this.xAxisStatus = xAxisStatus;
	}

	public int getYAxisStatus() {
		return yAxisStatus;
	}

	public void setYAxisStatus(int yAxisStatus) {
		this.yAxisStatus = yAxisStatus;
	}
}

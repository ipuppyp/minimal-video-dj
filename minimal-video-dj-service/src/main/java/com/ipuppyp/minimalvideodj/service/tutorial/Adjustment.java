package com.ipuppyp.minimalvideodj.service.tutorial;

public enum Adjustment {
	
	ZOOM_IN(0, 0, -20, -20), 
	ZOOM_OUT(0, 0, 20, 20), 
	MOVE_UP(-20, 20, 0, 0),
	MOVE_DOWN(20, -20, 0, 0),
	MOVE_LEFT(0, 0, -20, 20),
	MOVE_RIGHT(0, 0, 20, -20),
	RESET,
	CENTER,
	TOGGLE_FULL_SCREEN;

	
	private Adjustment() {
		this.top = 0;
		this.bottom = 0;
		this.left = 0;
		this.right = 0;
	}
	
	private Adjustment(int top, int bottom, int left, int right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	private final int top;
	private final int bottom;
	private final int left;
	private final int right;
	public int getTop() {
		return top;
	}
	public int getBottom() {
		return bottom;
	}
	public int getLeft() {
		return left;
	}
	public int getRight() {
		return right;
	}


	
}

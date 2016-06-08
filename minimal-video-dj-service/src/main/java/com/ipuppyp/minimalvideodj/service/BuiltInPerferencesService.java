package com.ipuppyp.minimalvideodj.service;

import java.util.prefs.Preferences;

public class BuiltInPerferencesService implements PreferencesService {

	private static final String TOP_BORDER = "top_border";
	private static final String BOTTOM_BORDER = "bottom_border";
	private static final String LEFT_BORDER = "left_border";
	private static final String RIGHT_BORDER = "right_border";
	
	private final Preferences prefs; 
	
	public BuiltInPerferencesService(Preferences prefs) {
		super();
		this.prefs = prefs;
	}

	@Override
	public int getTopBorder() {
		return prefs.getInt(TOP_BORDER, 0);
	}

	@Override
	public int getBottonBorder() {
		return prefs.getInt(BOTTOM_BORDER, 0);
	}

	@Override
	public int getLeftBorder() {
		return prefs.getInt(LEFT_BORDER, 0);
	}

	@Override
	public int getRightBorder() {
		return prefs.getInt(RIGHT_BORDER, 0);
	}
	
	@Override
	public void storeRightBorder(int rightBorder) {
		prefs.put(RIGHT_BORDER, "" + rightBorder);
	}

	@Override
	public void storeTopBorder(int topBorder) {
		prefs.put(TOP_BORDER, "" + topBorder);
		
	}

	@Override
	public void storeBottomBorder(int bottomBorder) {
		prefs.put(BOTTOM_BORDER, "" + bottomBorder);
		
	}

	@Override
	public void storeLeftBorder(int leftBorder) {
		prefs.put(LEFT_BORDER, "" + leftBorder);
		
	}
	
	
	
}
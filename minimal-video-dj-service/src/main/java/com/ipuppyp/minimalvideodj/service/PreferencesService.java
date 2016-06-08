package com.ipuppyp.minimalvideodj.service;

public interface PreferencesService {
	
	int getTopBorder();
	int getBottonBorder();
	int getLeftBorder();
	int getRightBorder();
	
	void storeTopBorder(int topBorder);
	void storeBottomBorder(int bottomBorder);
	void storeLeftBorder(int leftBorder);
	void storeRightBorder(int rightBorder);
}

package com.Util;

import java.util.Random;

public class CommonUtilities {

	public int RandomSelect(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}


	
}

/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	Singleton implementation of a pixels sort effect for the photo images, Java 
*	implementation of algorithm from
*
* 	https://github.com/kimasendorf/ASDFPixelSort
* 
* 
*/

package com.jason.glitchart.effects;

import java.util.ArrayList;

public class PixelSort {
	
	private static PixelSort instance = null;
	
	public final int 	blackValue = -10000000, //equivalent to rgb(103, 105, 128)
						whiteValue = -6000000,  //equivalent to rgb(164, 114, 128)
						brightnessValue = 60;
	
	//zero arg constructor
	protected PixelSort() { }
	
	//singleton pattern constraint
	public static PixelSort getInstance() {
		if (instance == null)
			instance = new PixelSort();
		
		return instance;
	}
	
	public int[][] sortBlack(int[][] data, int width, int height) {
		return null;
	}
		
	public int[][] sortWhite(int[][] data, int width, int height){
		return null;
	}
	
	
	/***
	 * 
	 * @param data
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @return int
	 * 
	 * 	Find the first column location that doesn't match the blackValue setting
	 */
	private int getFirstNotBlackY(ArrayList<Integer> data, int x, int y, int height, int width) {
		
		if (y < height) {
			while (data.get(x + y * width) < blackValue) {
				y++;
				if (y >= height)
					return -1;
			}
		}
		
		return y;
	}
	
	//Find the column location that is greater than the black value setting
	private int getNextBlackY(ArrayList<Integer> data, int x, int y, int height, int width) {
		if (y < height) {
			while (data.get(x + y * width) > blackValue) {
				y++;
				if (y >= height)
					return height - 1;
			}
		}
		
		return y - 1;
	}
	

}


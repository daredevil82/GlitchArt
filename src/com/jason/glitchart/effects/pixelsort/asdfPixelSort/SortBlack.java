package com.jason.glitchart.effects.pixelsort.asdfPixelSort;

import com.jason.glitchart.models.Photo;

/**
 * User: jason
 * Date: 11/27/13
 * Time: 5:26 PM
 * Description:
 */
public class SortBlack {

	private static final int BLACKVALUE = -10000000; //equivalent to rgb(103, 105, 128)

	public static int getFirstNotBlackX(int x, int y, Photo photo){

		int curX = x;

		while (photo.getPixelValue(curX, y) < BLACKVALUE){
			curX++;

			if (curX >= photo.getWidth())
				return photo.getWidth() - 1;
		}

		return curX;
	}

	public static int getFirstNotBlackY(int x, int y, Photo photo){
		int curY = y;

		if (curY < photo.getHeight()){
			while (photo.getPixelValue(x, curY) < BLACKVALUE){
				curY++;

				if (curY >= photo.getHeight())
					return -1;
			}
		}

		return curY;
	}

	public static int  getNextBlackX(int x, int y, Photo photo){
		int curX = x + 1;

		while (photo.getPixelValue(curX, y) > BLACKVALUE){
			curX++;

			if (curX > photo.getWidth())
				return -1;
		}

		return curX;
	}

	public static int getNextBlackY(int x, int y, Photo photo){
		int curY = y + 1;

		if (curY < photo.getHeight()){
			while (photo.getPixelValue(x, curY) > BLACKVALUE){
				curY++;

				if (curY >= photo.getHeight())
					return photo.getHeight() -1;
			}
		}

		return curY - 1;
	}
}

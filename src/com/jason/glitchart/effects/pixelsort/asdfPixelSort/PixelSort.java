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

package com.jason.glitchart.effects.pixelsort.asdfPixelSort;

import com.jason.glitchart.models.Photo;
import java.util.Arrays;

public class PixelSort {
	
	private static PixelSort instance = null;

	private int mode = 0,
				loops = 1;


	private Photo photo;
	
	//One arg constructor
	protected PixelSort(Photo photo) {
		this.photo = photo;
	}

	//two arg constructor
	protected PixelSort(Photo photo, int mode){
		this.photo = photo;
		this.mode = mode;
	}

	public static PixelSort getInstance(Photo photo){
		if (instance == null)
			instance = new PixelSort(photo);

		else {
			instance.photo = photo;
		}

		return instance;

	}
	
	//singleton pattern constraint
	public static PixelSort getInstance(Photo photo, int mode) {
		if (instance == null)
			instance = new PixelSort(photo, mode);

		else{
			instance.mode = mode;
			instance.photo = photo;
		}
		
		return instance;
	}

	public void setPhoto(Photo photo){
		this.photo = photo;
	}

	public void setMode(int mode){
		this.mode = mode;
	}

	public int getMode(){
		return this.mode;
	}

	private void applyEffect(){
		int row = 0,
			column = 0;

		while (column < photo.getWidth() - 1){
			sortColumn(column);
			column++;
		}

		while (row < photo.getHeight() - 1){
			sortRow(row);
			row++;
		}

		if (loops <= 0){


		} else{
			loops--;
			applyEffect();
		}


	}

	private void sortRow(int row){

		int x = 0,
			y = row,
			xEnd = 0,
			sortLength = 0;

		int[] values = null;

		while (xEnd < photo.getWidth() - 1){
			switch(this.mode){
				case 0:
					x = SortBlack.getFirstNotBlackX(x, y, photo);
					xEnd = SortBlack.getNextBlackX(x, y, photo);
					break;

				default:
					break;

			}

			if (x < 0)
				break;

			sortLength = xEnd - x;

			values = new int[sortLength];

			for (int i = 0; i < sortLength; i++)
				values[i] = photo.getPixelValue(x+1, y);

			Arrays.sort(values);

			for (int i = 0; i < sortLength; i++)
				photo.setPixelValue(x + i, y, values[i]);

			x = xEnd + 1;
		}

	}

	private void sortColumn(int column){

		int x = column,
			y = 0,
			yEnd = 0,
			sortLength = 0;

		int[] values = null;

		while(yEnd < photo.getHeight() - 1){
			switch (this.mode){
				case 0:
					y = SortBlack.getFirstNotBlackY(x, y, photo);
					yEnd = SortBlack.getNextBlackY(x, y, photo);
					break;

				default:
					break;
			}

			if (y < 0)
				break;

			sortLength = yEnd - y;
			values = new int[sortLength];

			for (int i = 0; i < sortLength; i++)
				values[i] = photo.getPixelValue(x, y + 1);

			Arrays.sort(values);

			for (int i = 0; i < sortLength; i++)
				photo.setPixelValue(x, y+i, values[i]);

			y = yEnd + 1;

		}

	}






}


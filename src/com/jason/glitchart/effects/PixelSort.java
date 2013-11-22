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

import com.jason.glitchart.models.Photo;

import java.util.ArrayList;

public class PixelSort {
	
	private static PixelSort instance = null;
	
	private final int 	BLACKVALUE = -10000000, //equivalent to rgb(103, 105, 128)
											WHITEVALUE = -6000000,  //equivalent to rgb(164, 114, 128)
											BRIGHTNESSVALUE = 60;

	private int mode;

	private Photo photo;
	
	//One arg constructor
	protected PixelSort(Photo photo) {
		this.photo = photo;
		this.mode = 0;
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
			instance.mode = 0;
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

	public Photo getPhoto(){
		return this.photo;
	}

	public int getMode(){
		return this.mode;
	}

	public int[][] sortBlack(int[][] data, int width, int height) {
		return null;
	}
		
	public int[][] sortWhite(int[][] data, int width, int height){
		return null;
	}
	
	private void setPixelValue(int x, int y, int val){
		//int offset = (x + y * width) * 4;
	}

}


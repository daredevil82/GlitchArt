/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	Controller for the Photo portion of the application
*/

package com.jason.glitchart.controllers;

import com.jason.glitchart.models.*;

import java.util.ArrayList;

public class PhotoController {

	private static PhotoController instance = null;
	
	public static PhotoController getInstance() {
		
		if (instance == null) 
			instance = new PhotoController();
		
		return instance;
		
	}
	
	protected PhotoController() {
		
	}
	
	public Photo photoInit(String file) {
		return new Photo(file);
	}
	
	public static void main(String[] args) {
		PhotoController pc = PhotoController.getInstance();
		Photo photo = pc.photoInit(args[0]);
		
		ArrayList<Integer> pixelList = photo.getPixelList();
		
		System.out.println("PixelList Values: " + pixelList.size() + "\nFirst Pixel: 0x" + Integer.toHexString(pixelList.get(0)).toUpperCase());
		
	}
	
}


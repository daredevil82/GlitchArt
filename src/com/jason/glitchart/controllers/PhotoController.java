/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	Controller for the Photo portion of the application
*/

package com.jason.glitchart.controllers;
import com.jason.glitchart.models.*;

import java.awt.image.BufferedImage;


public class PhotoController {

	private static PhotoController instance = null;
	private Photo photo = null;
	
	public static PhotoController getInstance() {
		
		if (instance == null) 
			instance = new PhotoController();
		
		return instance;
		
	}

	//zero arg constructor
	protected PhotoController() {
		
	}
	
	public void photoInit(String file) {
		this.photo =  new Photo(file);
	}

	public Photo getPhoto(){
		return this.photo;
	}

	public Photo applyEffect(Photo photo, int effect){
		return null;
	}

	public Photo applyEffect(Photo photo){
		return null;
	}

	public void saveImage(BufferedImage image){
		this.photo.saveImage(image);
	}
	
}


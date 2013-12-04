package com.jason.glitchart.views;

import com.jason.glitchart.models.*;
import com.jason.glitchart.controllers.PhotoController;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 11/22/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class GlitchArtCommandLine {

	public static GlitchArtCommandLine instance = null;

	//zero arg constructor
	protected GlitchArtCommandLine() { }

	public static GlitchArtCommandLine getInstance(){
		if (instance == null)
			instance = new GlitchArtCommandLine();

		return instance;

	}

	public void handleArgs(String file, int effect){
		PhotoController pc = PhotoController.getInstance();
		pc.photoInit(file);

		Photo photo = pc.getPhoto();

		int[] pixels = photo.getOriginal1DArray();

		System.out.printf("Pixel Count: %d\n\nFirst 5 Pixel Values:\n", (photo.getWidth() * photo.getHeight()));


		for (int i = 0; i < 5; i++){
			System.out.printf("\tpixels[%d]:\t%d\t%s\n",
								 i, pixels[i], new Color(pixels[i]));
		}

		pc.saveImage(photo.getOldPhoto());

	}


}

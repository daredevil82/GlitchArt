/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	In memory representation of the provided image, with various
*	processing functionality that are used to feed data to effects.
*/

package com.jason.glitchart.models;
import com.jason.glitchart.utils.*;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Photo {

	private String currentPath = System.getProperty("user.dir");
	
	private File file = null;
	
	private BufferedImage photo = null;

	int[] buffer1D = null;
	
	public Photo(String file) {
		try {

			this.file = FileUtils.openFile(file);
			this.photo = ImageIO.read(this.file);

		} catch (IOException e) {
			System.err.print("ImageIO cannot read the file with the supplied path:\t" + file + "\n\nExiting now...");
			System.exit(1);
		}
	}

	public int[] get1DBufferArray(){
		if (this.buffer1D == null)
			this.buffer1D = PhotoUtils.getPixelArray(this.photo);

		return this.buffer1D;
	}

	public int getWidth(){
		return this.photo.getWidth();
	}

	public int getHeight(){
		return this.photo.getHeight();
	}

	public int getPixelValue(int x, int y){
		if (this.buffer1D == null)
			this.buffer1D = PhotoUtils.getPixelArray(this.photo);

		return this.buffer1D[x + y];
	}

	public void setPixelValue(int x, int y, int value){
		this.buffer1D[x + y] = value;
	}

	public boolean saveImage(BufferedImage image){
		return FileUtils.saveImage(image, this.file);
	}

	public boolean saveImage(int[] buffer, int width, int height){
		BufferedImage newImage = PhotoUtils.newImage(buffer, width, height);
		return saveImage(newImage);
	}

}


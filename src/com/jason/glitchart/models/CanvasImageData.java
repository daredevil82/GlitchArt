package com.jason.glitchart.models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * User: jason
 * Date: 12/5/13
 * Time: 7:20 PM
 * Description:  Java implementation of the HTML5 CanvasImageData object
 *
 * 	imageData has four integer values in a RGBA array.  Therefore, each four-block
 * 	segment of the array corresponds to a single integer pixel value.
 */
public class CanvasImageData {

	private int[] imageData;
	private BufferedImage image;

	public CanvasImageData(BufferedImage image){
		this.image = image;
		init(getPixels(this.image));
	}

	public CanvasImageData(int[] pixels){
		this.image = null;
		init(pixels);
	}

	private byte[] getPixels(BufferedImage image){
		return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	}

	//converts image byte values to a 1D integer array in the same format as the CanvasImageData spec.
	private void init(byte[] pixels){

		this.imageData = new int[pixels.length * 4];

		for (int i = 0; i < pixels.length; i += 4){
			this.imageData[i] = pixels[i] & 255;	//red channel
			this.imageData[i + 1] = pixels[i + 1] & 255; //green channel
			this.imageData[i + 2] = pixels[i + 2] & 255; //blue channel

		}
	}

	//convenience method, converts an existing 1D array of integer color values into a CanvasImageData spec
	private void init(int[] pixels){
		this.imageData = new int[pixels.length * 4];

		for (int i = 0; i < pixels.length; i++){
			this.imageData[i] = (pixels[i] >> 16) & 255; 	//red channel
			this.imageData[i + 1] = (pixels[i] >> 8) & 255;	//green channel
			this.imageData[i + 2] = pixels[i] & 255; 		//blue channel
			this.imageData[i + 3] = 255;					//alpha channel
		}


	}

	//getter for pixel data
	public int[] getData(){
		return this.imageData;
	}

	//set an integer color value at a given location.
	public void setPixelValue(int x, int y, int value){

		int offset = (x + y * this.image.getWidth()) * 4;
		this.imageData[offset] = (value >> 16) & 255;
		this.imageData[offset + 1] = (value >> 8) & 255;
		this.imageData[offset + 2] = value & 255;

	}

	//get the integer color value from a given location
	public int getPixelValue(int x, int y){
		int offset = (x + y * this.image.getWidth()) * 4;

		return (((255 << 8) | this.imageData[offset]) << 8 | this.imageData[offset + 1]) << 8 | this.imageData[offset + 2];

	}

	//Convenience getter method to return a Color object rather than an integer value
	public Color getColorValue(int x, int y){
		return new Color(getPixelValue(x, y));
	}

	//convenience setter method to use a Color value rather than an integer value
	public void setColorValue(int x, int y, Color color){
		setPixelValue(x, y, color.getRGB());
	}

	//calculate the brightness value of the given location
	public int getPixelBrightness(int x, int y){
		int offset = (x + y * this.image.getWidth()) * 4;
		int max = CanvasImageData.tripleMax(this.imageData[offset], this.imageData[offset + 1], this.imageData[offset + 2]);
		return max / 255 * 100;

	}

	public static int tripleMax(int x, int y, int z){
		if (x > y && x > z)
			return x;

		if (y > x && y > z)
			return y;

		return z;
	}

	public boolean saveImage(CanvasImageData data){
		return false;
	}

}

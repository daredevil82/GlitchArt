/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	In memory representation of the provided image, with various
*	processing functionality that are used to feed data to effects.
*/

package com.jason.glitchart.models;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Photo {

	private String currentPath = System.getProperty("user.dir");
	
	private File file = null;
	
	private BufferedImage 	oldPhoto = null,
							newPhoto = null;

	private int[] 	original1DArray = null,
					altered1DArray = null;

	private int[][] original2DArray = null,
					altered2DArray = null;
	
	private boolean hasAlphaChannel = false,
					imageChanged;
	
	
	public Photo(String file) {
		try {
			this.file = new File(file);

			if (!this.file.exists()){
				System.out.println("File doesn't exist at defined path, trying with current path...\n" +
					this.currentPath + file);
				this.file = new File(this.currentPath + file);
			}

			this.oldPhoto = ImageIO.read(this.file);
			this.imageChanged = false;
			this.hasAlphaChannel = this.oldPhoto.getAlphaRaster() != null;
			getPixelArray();

			
		} catch (IOException e) {
			System.err.print("ImageIO cannot read the file with the supplied path:\t" + file + "\n\nExiting now...");
			System.exit(1);
		}
	}


	//Thanks to StackOverflow user Mota for his answer http://stackoverflow.com/a/9470843/214892
	private void getPixelArray(){
		byte[] pixels = ((DataBufferByte) this.oldPhoto.getRaster().getDataBuffer()).getData();

		this.original2DArray = new int[this.oldPhoto.getHeight()][this.oldPhoto.getWidth()];

		int 	pixelLength = 4,
				argb = -1;

		long start = 0L;

		if (hasAlphaChannel){

			start = System.currentTimeMillis();
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength){
				argb = 0;
				argb += (((int) pixels[pixel] & 0xFF) << 24); //alpha channel
				argb += (((int) pixels[pixel + 1] & 0xFF)); //blue channel
				argb += (((int) pixels[pixel + 2] & 0xFF) << 8); //green channel
				argb += (((int) pixels[pixel + 3] & 0xFF) << 16); //red channel

				this.original2DArray[row][col] = argb;
				col++;

				if (col == getWidth()){
					col = 0;
					row++;
				}
			}
		} else {
			start = System.currentTimeMillis();
			pixelLength = 3;

			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength){
				argb = -16777216; //set to 255 for alpha channel
				argb += (((int) pixels[pixel] & 0xFF)); //blue channel
				argb += (((int) pixels[pixel + 1] & 0xFF) << 8); //green channel
				argb += (((int) pixels[pixel + 2] & 0xFF) << 16); //red channel

				this.original2DArray[row][col] = argb;
				col++;

				if (col == getWidth()){
					col = 0;
					row++;
				}

			}
		}
		double elapsed = (double) (System.currentTimeMillis() - start);

		System.out.printf("Parsing image into 2D array elapsed time:\t%f\n", elapsed);
	}

	private void flattenArray(int[][] pixels, int[] flatten){

		ArrayList<Integer> tempList = new ArrayList<>();

		for (int i = 0; i < getHeight(); i++){
			for (int j = 0; j < pixels[i].length; j++){
				tempList.add(pixels[i][j]);
			}
		}

		for (int i = 0; i < flatten.length; i++){
			flatten[i] = tempList.get(i);
		}

	}

	public int[] getOriginal1DArray(){
		if (this.original1DArray == null){
			this.original1DArray = new int[getWidth() * getHeight()];

			long start = System.currentTimeMillis();

			flattenArray(this.original2DArray, this.original1DArray);

			double elapsed = (double)(System.currentTimeMillis() - start);

			System.out.printf("Flattening 2D array elapsed time:\t%f\n", elapsed);
		}

		return this.original1DArray;

	}

	public int[][] getOriginal2DArray(){
		return this.original2DArray;
	}

	public BufferedImage getOldPhoto(){
		return this.oldPhoto;
	}

	public BufferedImage getNewPhoto(){
		if (this.oldPhoto == null || imageChanged)
			regenerateImage();

		return this.newPhoto;
	}

	public int getWidth(){
		return this.oldPhoto.getWidth();
	}

	public int getHeight(){
		return this.oldPhoto.getHeight();
	}

	public int getOriginalPixelValue(int x, int y){
		return this.original2DArray[x][y];
	}

	public void setNewPixelValue(int x, int y, int value){
		if (!this.imageChanged)
			this.imageChanged = true;

		this.altered1DArray[x + y] = value;
	}

	public void buildNew2DArray(){
		this.altered2DArray = new int[getHeight()][getWidth()];

		int width = getWidth();

		for (int i = 0; i < this.altered2DArray.length; i += width){
			this.altered2DArray[i] = Arrays.copyOfRange(this.altered1DArray, i, width);
		}
	}

	private void regenerateImage(){
		this.newPhoto = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Color color = null;

		for (int x = 0; x < this.altered2DArray.length; x++){
			for (int y = 0; y < this.altered2DArray[x].length; y++){
				this.newPhoto.setRGB(x, y, this.altered2DArray[x][y]);
			}
		}
	}
}


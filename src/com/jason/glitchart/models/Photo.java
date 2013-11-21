/**
*	Author: Jason
*	Date : Nov 20, 2013
*	Description : 
*
*	In memory representation of the provided image, 
*	returns either an ArrayList<Integer> or int[][] 
*	array of pixel color values for effect processing 
*/

package com.jason.glitchart.models;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.ArrayList;

public class Photo {
	
	private File 	file = null;
	
	private	 int 	width = 0,
					height = 0,
					offset = 3;
	
	private BufferedImage 	oldPhoto = null, 
							newPhoto = null;
	
	private int[][] pixelArray;
	private ArrayList<Integer> pixelList;
	
	private boolean hasAlphaChannel = false;
	
	
	public Photo(String file) {
		try {
			this.file = new File(file);
			this.oldPhoto = ImageIO.read(this.file);
			parseImage();
			
		} catch (IOException e) { }
	}
	
	
	/**
	 * 	Internal class method that parses the BufferedImage file's pixel values
	 * 	into ints and fills both int[][] array and ArrayList<Integer> structures 
	 * 
	 * 	Credit goes to StackOverflow user Mota and his answer 
	 * 	http://stackoverflow.com/a/9470843/214892
	 */
	
	private void parseImage() {
		byte[] pixels = ((DataBufferByte) this.oldPhoto.getRaster().getDataBuffer()).getData();
		
		this.width = this.oldPhoto.getWidth();
		this.height = this.oldPhoto.getHeight();
		
		this.hasAlphaChannel = this.oldPhoto.getAlphaRaster() != null;
		
		this.pixelArray = new int[this.height][this.width];
		
		if (this.hasAlphaChannel) {
			
			this.offset = 4;
			
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += offset) {
				int argb = 0;
				
				argb += (((int) pixels[pixel] & 0xFF) << 24); //alpha channel
				argb += (((int) pixels[pixel + 1] & 0xFF)); //blue
				argb += (((int) pixels[pixel + 2] & 0xFF) << 8); //green;
				argb += (((int) pixels[pixel + 3] & 0xFF) << 16); //blue
				
				this.pixelArray[row][col] = argb;
				
				col++;
				
				if (col == width) {
					row++;
					col = 0;
				}
				
			}
			
		} else {
			
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += offset) {
				int argb = 0;
				
				argb += -16777216; //255 alpha channel
				argb += (((int) pixels[pixel] & 0xFF)); //blue
				argb += (((int) pixels[pixel + 1] & 0xFF) << 8); //green
				argb += (((int) pixels[pixel + 2] & 0xFF) << 16); //red
				
				this.pixelArray[row][col] = argb;
				col++;
				
				if (col == width) {
					row++;
					col = 0;
				}
			}
		}
		
		flattenArray();
	}
	
	/**
	 * 	Internal helper method that parses the existing pixel 2-D array
	 * 	into an ArrayList<Integer>
	 */
	private void flattenArray() {
		this.pixelList = new ArrayList<Integer>(this.width * this.height);
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.pixelArray[i].length; j++) {
				this.pixelList.add(this.pixelArray[i][j]);
			}
		}
	}
	
	/**
	 *	Getter for the 2-D pixel array of color values
	 * @return
	 */
	public int[][] getPixelArray() {
		return this.pixelArray;
	}
	
	/**
	 * 	Getter for the 1-D ArrayList of color values
	 * @return
	 */
	public ArrayList<Integer> getPixelList(){
		return this.pixelList;
	}

}


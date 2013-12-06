package com.jason.glitchart.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import com.google.common.primitives.Ints;

/**
 * User: jason
 * Date: 12/5/13
 * Time: 1:50 PM
 * Description:
 *
 * 	Utility and convenience methods for Photo or Image objects
 */
public class PhotoUtils {

	//convert BufferedImage to use integer color values in place of java.awt.Color classes.
	//Thanks to StackOverflow user Mota for his answer at http://stackoverflow.com/a/9470843/214892
	public static int[][] getDataBuffer(BufferedImage image){

		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		int[][] buffer = new int[image.getHeight()][image.getWidth()];

		int	pixelLength = 4,
			argb = -1;

		if (image.getAlphaRaster() != null){
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength){
				argb = 0;
				argb += (((int) pixels[pixel] & 0xFF) << 24); //alpha channel
				argb += (((int) pixels[pixel + 1] & 0xFF)); //blue channel
				argb += (((int) pixels[pixel + 2] & 0xFF) << 8); //green channel
				argb += (((int) pixels[pixel + 3] & 0xFF) << 16); //red channel

				buffer[row][col] = argb;
				col++;

				if (col == image.getWidth()){
					col = 0;
					row++;
				}
			}
		}  else {
			pixelLength = 3;

			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength){
				argb = -16777216; //set to 255 for alpha channel
				argb += (((int) pixels[pixel] & 0xFF)); //blue channel
				argb += (((int) pixels[pixel + 1] & 0xFF) << 8); //green channel
				argb += (((int) pixels[pixel + 2] & 0xFF) << 16); //red channel

				buffer[row][col] = argb;
				col++;

				if (col == image.getWidth()){
					col = 0;
					row++;
				}

			}

		}

		return buffer;

	}

	//Convenience method to convert an image object into a 1D buffer array of integer color values
	public static int[] getPixelArray(BufferedImage image){
		return getPixelArray(getDataBuffer(image));
	}

	//uses Google's Guava library to flatten the 2D array into a 1D buffer
	public static int[] getPixelArray(int[][] buffer){
		return Ints.concat(buffer);
	}

	//converts a 1D buffer array to a BufferedImage with width x height dimensions
	public static BufferedImage newImage(int[] buffer, int width, int height){
		return null;
	}
}

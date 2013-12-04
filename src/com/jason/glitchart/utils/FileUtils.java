package com.jason.glitchart.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: jason
 * Date: 12/4/13
 * Time: 2:15 PM
 * Description:
 */
public class FileUtils {

	public static File openFile(String filePath){

		String path = System.getProperty("user.dir");
		File file = new File(filePath);

		if (!file.exists()) {
			System.out.println("File doesn't exist at defined path, trying with current path...\n" +
									   path + file);
			file = new File(path + file);
		}

		return file;

	}

	public static boolean saveImage(BufferedImage image, File file){

		try{
			String timeStamp = new SimpleDateFormat("yyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

			String name = file.getName();
			String[] fileName = name.split("\\.(?=[^\\.]+$)");
			File newFile = new File(System.getProperty("user.dir") + "/" + fileName[0] + "_" +  timeStamp + "." + fileName[1]);

			ImageIO.write(image, fileName[1], newFile);

			if (newFile.exists()){
				System.out.println("Done");
				return true;
			}

		}catch (IOException e){
			e.printStackTrace();
		}

		return false;
	}

}

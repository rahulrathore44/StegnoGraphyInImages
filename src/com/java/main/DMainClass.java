/**
 * 
 */
package com.java.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.java.utility.SourceFileReader;
import com.sun.org.apache.xpath.internal.FoundIndex;

/**
 * @author - rahul.rathore
 * @date - 26-May-2015
 * @project - StegnoGraphy
 * @package - com.java.main
 * @file name - MainClass.java
 */
public class DMainClass {
	
	private static BufferedImage scrImage;
	private static File srcImageFile;
	private static File txtFile;
	private static int[] rgbArray;
	private static FileInputStream _readFileInputStream;
	private static byte[] buffer = new byte[1];
	private static FileOutputStream _writeFile;
	
	public static void main(String[] args) {
		srcImageFile = new File("t.bmp");
		int index = 0;
		try {
			scrImage = ImageIO.read(srcImageFile);
			rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
			rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(),  rgbArray, 0, scrImage.getWidth());
			String s ="";
			_writeFile = new FileOutputStream(new File("d.txt"));
			for(int i = 0; i < rgbArray.length; i++){
				int b = ((rgbArray[i] ) & 0x000000001);
				if(b == 0)
					b = 48;
				else
					b = 49;
				_writeFile.write(b);
			}
			_writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

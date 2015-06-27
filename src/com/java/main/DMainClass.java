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

import com.java.encrypt.Dcrypt;
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
		srcImageFile = new File("Capture.bmp-27-06-2015-13-04.bmp");
		int index = 0;
		try {
			scrImage = ImageIO.read(srcImageFile);
			rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
			rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(),  rgbArray, 0, scrImage.getWidth());
			String s ="";
			_writeFile = new FileOutputStream(new File("d.txt"));
			int pixelCount = Dcrypt.getPixelCount(rgbArray);
			int msgLength = Dcrypt.getMsgLength(rgbArray, pixelCount);
			int msgIniCounter = (( 8 + pixelCount));
			int msgEndCounter = (msgLength * 8);
			for(int i = msgIniCounter; i < (msgEndCounter + msgIniCounter); i++){
				int b = ((rgbArray[i] ) & 0x000000001);
				if(b == 0)
					b = 48;
				else
					b = 49;
				_writeFile.write(b);
			}
			_writeFile.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

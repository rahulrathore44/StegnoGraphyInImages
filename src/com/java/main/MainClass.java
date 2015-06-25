/**
 * 
 */
package com.java.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.java.utility.SourceFileReader;

/**
 * @author - rahul.rathore
 * @date - 26-May-2015
 * @project - StegnoGraphy
 * @package - com.java.main
 * @file name - MainClass.java
 */
public class MainClass {
	
	private static BufferedImage scrImage;
	private static BufferedImage scrImage2;
	private static File srcImageFile;
	private static File txtFile;
	private static int[] rgbArray;
	private static FileInputStream _readFileInputStream;
	private static byte[] buffer = new byte[1];
	
	public static void main(String[] args) {
		try {
			txtFile = new File(SourceFileReader.convertToBinary("C:\\Users\\rahul.rathore\\Desktop\\TestFile.txt"));
			_readFileInputStream = new FileInputStream(txtFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		srcImageFile = new File("C:\\Users\\rahul.rathore\\Desktop\\Capture.bmp");
		int index = 0;
		try {
			scrImage = ImageIO.read(srcImageFile);
			rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
			rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(),  rgbArray, 0, scrImage.getWidth());
			while(_readFileInputStream.read(buffer) != -1){
				int a = ((rgbArray[index] >> 24) & 0x000000ff);
				int r = ((rgbArray[index] >> 16) & 0x000000ff);
				int g = ((rgbArray[index] >> 8) & 0x000000ff);
				int b = ((rgbArray[index] ) & 0x000000fe);
				System.out.println(b);
				if((buffer[0] - 48) == 0)
					b = ( b |(0x00000000));
				else
					b = (b | (0x00000001));
				
				System.out.println("-" + b);
				rgbArray[index] = ( a << 24) | (r << 16) | ( g << 8) | b;
				index++;
				
			}
			scrImage2 = new BufferedImage(scrImage.getWidth(), scrImage.getHeight(), scrImage.getType());
			scrImage2.setRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(), rgbArray, 0, scrImage.getWidth());
			System.out.println(ImageIO.write(scrImage2, "bmp", new File("t.bmp")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

/**
 * 
 */
package com.java.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.java.encrypt.Encrypt;
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
	private static BufferedImage ecrImage;
	private static File srcImageFile;
	private static File txtFile;
	private static File lengthFile;
	private static int[] rgbArray;
	private static FileInputStream _readFileInputStream;
	private static FileInputStream _lengthFileInputStream;
	private static String pixelCount;
	private static String fileName;
	private static final String Image_TYPE = "bmp";

	public static void main(String[] args) throws IOException {

		txtFile = new File(
				SourceFileReader
						.convertToBinary("C:\\Users\\rahul.rathore\\Desktop\\TestFile.txt"));
		lengthFile = new File("length.txt");
		pixelCount = SourceFileReader
				.appendZero(((int) lengthFile.length()) + "");

		_lengthFileInputStream = new FileInputStream(lengthFile);
		_readFileInputStream = new FileInputStream(txtFile);

		srcImageFile = new File(
				"C:\\Users\\rahul.rathore\\Desktop\\Capture.bmp");
		
		fileName = srcImageFile.getName();

		scrImage = ImageIO.read(srcImageFile);

		rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
		rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),
				scrImage.getHeight(), rgbArray, 0, scrImage.getWidth());

		rgbArray = Encrypt.hideCountPixel(rgbArray, pixelCount);
		rgbArray = Encrypt.hideData(rgbArray, _lengthFileInputStream);
		rgbArray = Encrypt.hideData(rgbArray, _readFileInputStream);

		ecrImage = new BufferedImage(scrImage.getWidth(), scrImage.getHeight(),
				scrImage.getType());
		ecrImage.setRGB(0, 0, scrImage.getWidth(), scrImage.getHeight(),
				rgbArray, 0, scrImage.getWidth());
		
		ImageIO.write(ecrImage, Image_TYPE, new File(SourceFileReader.getFileName(fileName)));
		System.out.println("Done");
	}

}

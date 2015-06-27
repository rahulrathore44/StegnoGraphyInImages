/**
 * 
 */
package com.java.crypto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.java.cryptoutils.DecryptUtil;
import com.java.utility.DecryptMessage;


public class DecryptMainClass {
	
	private static BufferedImage scrImage;
	private static File srcImageFile;
	private static File binaryData;
	private static FileOutputStream _writeFile;
	private static int blue = 0;
	private static int[] rgbArray;
	
	public static boolean DecryptDataFromImg(String aImgPath) {
		try {
			srcImageFile = new File(aImgPath);
			binaryData = new File("d.txt");

			scrImage = ImageIO.read(srcImageFile);
			rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
			
			rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(), rgbArray, 0, scrImage.getWidth());

			_writeFile = new FileOutputStream(binaryData);

			int pixelCount = DecryptUtil.getPixelCount(rgbArray);
			int msgLength = DecryptUtil.getMsgLength(rgbArray, pixelCount);
			int msgIniCounter = ((8 + pixelCount));
			int msgEndCounter = (msgLength * 8);

			for (int i = msgIniCounter; i < (msgEndCounter + msgIniCounter); i++) {
				blue = ((rgbArray[i]) & 0x000000001);
				if (blue == 0)
					blue = 48;
				else
					blue = 49;
				_writeFile.write(blue);
			}

			DecryptMessage.writeMessage(binaryData);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(_writeFile != null){
					_writeFile.close();
					_writeFile = null;
				}
					
			} catch (Exception ex) {
				// Ignored
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(DecryptDataFromImg("Capture.bmp-27-06-2015-21-19.bmp"));
	}
}

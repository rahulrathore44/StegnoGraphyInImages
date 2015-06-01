/**
 * 
 */
package com.java.main;

import java.awt.image.BufferedImage;
import java.io.File;
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
	public static void main(String[] args) {
		try {
			System.out.println(SourceFileReader.convertToBinary(""));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedImage bm = null;
		File f = new File("C:\\Users\\rahul.rathore\\Desktop\\Capture.bmp");
		int []rgb = new int[(int)f.length()];
		try {
			bm = ImageIO.read(f);
			rgb = bm.getRGB(0, 0, 130, 63, rgb, 0, 63);
			int r = (rgb[0] & 0x00ff0000) >> 16;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

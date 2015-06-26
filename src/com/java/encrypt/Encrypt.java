/**
 * 
 */
package com.java.encrypt;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author - rahul.rathore
 * @date - 26-Jun-2015
 * @project - StegnoGraphy
 * @package - com.java.encrypt
 * @file name - Encrypt.java
 */
public class Encrypt {
	
	private static byte buffer[] = new byte[1];
	private static int index = 8;
	
	public static int[] hideCountPixel(int rgb[],String bCount) {
		for(int  i = 0; i < 8; i++){
			rgb[i] =  rgb[i] & 0xfffffffe;
			rgb[i] = rgb[i] | ((Integer.parseInt(bCount.charAt(i)+ "")));
		}
		return rgb;
	}
	
	public static int[] hideData(int rgb[],FileInputStream bFins) throws IOException {
		
		while(bFins.read(buffer) != -1){
			rgb[index] = rgb[index] & 0xfffffffe;
			if((buffer[0] - 48) == 0)
				rgb[index] = ( rgb[index] |(0x00000000));
			else
				rgb[index] = (rgb[index] | (0x00000001));
			
			index++;
		}
		return rgb;
	}

}

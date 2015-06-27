/**
 * 
 */
package com.java.cryptoutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class EncryptUtil {
	
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
	
	public static boolean cleanFile(File aFile) {
		try {
				if(aFile.exists())
					aFile.deleteOnExit();
				return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}

/**
 * 
 */
package com.java.cryptoutils;


public class DecryptUtil {
	
	public static int convertToIntFromBinary(String aBinaryValue) {
		int value = 0;
		int powe = 0;
		for(int i = (aBinaryValue.length() - 1); i >=0; i-- ){
			value = value + (int)(Integer.parseInt(aBinaryValue.charAt(i) + "") * Math.pow(2, powe++)); 
		}
		return value;
	}
	
	public static int getPixelCount(int []rgb) {
		String pixelCount = "";
		for(int i = 0; i < 8; i++){
			pixelCount = pixelCount + (rgb[i] & 0x00000001);
		}
		return convertToIntFromBinary(pixelCount);
	}
	
	public static int getMsgLength(int []rgb,int pixelCount) {
		String msgLength = "";
		int lengthIndex = 8;
		for(int i = 0; i < pixelCount; i++){
			msgLength = msgLength + (rgb[lengthIndex++] & 0x00000001);
		}
		return convertToIntFromBinary(msgLength);
	}

}

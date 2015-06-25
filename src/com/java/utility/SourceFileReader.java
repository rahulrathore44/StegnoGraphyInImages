/**
 * 
 */
package com.java.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author - rahul.rathore
 * @date - 26-May-2015
 * @project - StegnoGraphy
 * @package - com.java.utility
 * @file name - SourceFileReader.java
 */
public class SourceFileReader {
	
	private static File srcFile;
	private static File tempFile;
	private static File lenFile;
	private static FileInputStream readFile;
	private static BufferedWriter writeFile;
	private static final int bufSize = 1024;
	private static byte buffer[] = null;
	
	private static byte[] clearBuffer(byte[] buffer) {
		for(int i = 0; i < buffer.length; i++)
			buffer[i] = 0;
		
		return buffer;
	}
	private static String getBinary(byte aValue) {
		String binaryValue = Integer.toBinaryString(aValue);
		int totalZero = 8 - binaryValue.length();
		for(int i = 0; i < totalZero; i++)
			binaryValue = "0" + binaryValue;
		return binaryValue;
	}
	
	private static void writeLengthToFile(int length) throws IOException {
		String binaryValue = Integer.toBinaryString(length);
		
		int totalZero = 8 - binaryValue.length();
		for(int i = 0; i < totalZero; i++)
			binaryValue = "0" + binaryValue;
		
		lenFile = new File("length.txt");
		writeFile = new BufferedWriter(new FileWriter(lenFile));
		writeFile.write(binaryValue);
		writeFile.close();
		
	}
	
	public static String convertToBinary(String fileName) throws IOException {
		
		srcFile = new File(fileName);
		tempFile = new File("temp.txt");
		writeLengthToFile((int)srcFile.length());
		
		if((int)srcFile.length() > bufSize)
			buffer = new byte[bufSize];
		else
			buffer = new byte[(int)srcFile.length()];
		
			readFile = new FileInputStream(srcFile);
			writeFile = new BufferedWriter(new FileWriter(tempFile));
			
			while(readFile.read(buffer)!= -1){
				for(int i = 0; i < buffer.length;){
					if(buffer[i] == 0)
						break;
					writeFile.write(getBinary(buffer[i++]));
				}
				buffer = clearBuffer(buffer);
			}
			writeFile.close();
		return tempFile.getCanonicalPath();
	}

}

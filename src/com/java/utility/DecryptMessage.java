/**
 * 
 */
package com.java.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.java.cryptoutils.EncryptUtil;


public class DecryptMessage {
	
	private static File srcFile;
	private static FileInputStream srcInputStream;
	private static BufferedWriter msgWriter;
	private static String message = "";
	private static byte buffer[] = new byte[8];
	

	public static void writeMessage(File aMsgObject) {
		
		try {
				srcFile = aMsgObject;
				srcInputStream = new FileInputStream(srcFile);
				
				int charCode = 0;
				String info = "";
				
				while(srcInputStream.read(buffer)!= -1){
					info = new String(buffer, 0, buffer.length, "ASCII");
					charCode = Integer.parseInt(info, 2);
					message =  message + (new Character((char)charCode)).toString();
				}
				
				msgWriter = new BufferedWriter(new FileWriter(new File(SourceFileReader.getFileName(FileType.FILE_NAME,FileType.TEXT))));
				msgWriter.write(message);
				
				if(!EncryptUtil.cleanFile(srcFile)){
					//Logger.WARN();
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(srcInputStream != null){
					srcInputStream.close();
					srcInputStream = null;
				}
				if(msgWriter != null){
					msgWriter.close();
					msgWriter = null;
				}		
			} catch (Exception e) {
				// omitted
			}
		}
		
	}

}

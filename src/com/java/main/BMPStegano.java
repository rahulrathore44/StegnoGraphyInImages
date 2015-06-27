/**
 * 
 */
package com.java.main;

import com.java.crypto.DecryptMainClass;
import com.java.crypto.EncryptMainClass;

public class BMPStegano {

	
	public static void main(String[] args) {
		
		switch (args.length) {
		case 1:
			DecryptMainClass.DecryptDataFromImg(args[0]);
			break;
		case 2:
			EncryptMainClass.EncryptDataToImage(args[0], args[1]);
			break;
		default:
			throw new RuntimeException("Invalid Argument");
		}

	}

}

/**
 * 
 */
package com.java.crypto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.java.cryptoutils.EncryptUtil;
import com.java.utility.FileType;
import com.java.utility.SourceFileReader;

public class EncryptMainClass {

	private static BufferedImage scrImage;
	private static BufferedImage ecrImage;
	private static File srcImageFile;
	private static File txtFile;
	private static File lengthFile;
	private static FileInputStream _readFileInputStream;
	private static FileInputStream _lengthFileInputStream;
	private static String pixelCount;
	private static String fileName;
	private static int[] rgbArray;
	
	
	public static boolean EncryptDataToImage(String bImgFile,String aMsgFile) {
		try {

			txtFile = new File(SourceFileReader.convertToBinary(aMsgFile));
			lengthFile = new File(FileType.ENC_LENGTH_FILE);
			pixelCount = SourceFileReader.appendZero(Integer.toBinaryString((int) lengthFile.length()));

			_lengthFileInputStream = new FileInputStream(lengthFile);
			_readFileInputStream = new FileInputStream(txtFile);

			srcImageFile = new File(bImgFile);

			fileName = srcImageFile.getName();

			scrImage = ImageIO.read(srcImageFile);

			rgbArray = new int[scrImage.getHeight() * scrImage.getWidth()];
			rgbArray = scrImage.getRGB(0, 0, scrImage.getWidth(),scrImage.getHeight(), rgbArray, 0, scrImage.getWidth());

			rgbArray = EncryptUtil.hideCountPixel(rgbArray, pixelCount);
			rgbArray = EncryptUtil.hideData(rgbArray, _lengthFileInputStream);
			rgbArray = EncryptUtil.hideData(rgbArray, _readFileInputStream);

			ecrImage = new BufferedImage(scrImage.getWidth(),scrImage.getHeight(), scrImage.getType());
			ecrImage.setRGB(0, 0, scrImage.getWidth(), scrImage.getHeight(),rgbArray, 0, scrImage.getWidth());
			
			if(!EncryptUtil.cleanFile(txtFile) || !EncryptUtil.cleanFile(lengthFile)){
				// logger.WARN();
			}
			
			return ImageIO.write(ecrImage, FileType.Image_TYPE, new File(SourceFileReader.getFileName(fileName,FileType.BMP)));
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		
		}finally{
			try{
				if(_readFileInputStream != null){
					_readFileInputStream.close();
					_readFileInputStream = null;
				}
				if(_lengthFileInputStream != null)
				{
					_lengthFileInputStream.close();
					_lengthFileInputStream = null;
				}
			}catch(IOException e){
				//omitted.
			}
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(EncryptDataToImage("C:\\Users\\rahul.rathore\\Desktop\\TestFile.txt","C:\\Users\\rahul.rathore\\Desktop\\Capture.bmp"));
	}*/
}


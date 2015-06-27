# DESCRIPTION

Steganography is the practice of concealing a file, message, image, or video within another file, message, image, or video. The word steganography combines the Greek words steganos, meaning "covered, concealed, or protected", and graphein meaning "writing!"


# REQUIREMENTS

* JDK 1.7 

# COMMAND LINE

Encryption
 
	* java -jar BMPStegano.1.0.0.jar "<Bmp Image Path>" "<Message File Path>"
	
Decryption

	* java -jar BMPStegano.1.0.0.jar "<Encrypted Bmp Image Path>"
	
# IMPORT JAR	

Encryption
 
	* EncryptMainClass.EncryptDataToImage("<Bmp Image File>","<Message File Path>")
	
Decryption

	* DecryptMainClass.DecryptDataFromImg("<Encrypted Bmp Image Path>")



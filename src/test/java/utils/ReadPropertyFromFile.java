package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ReadPropertyFromFile {
	public static String propertyFilePath = "src/test/resources/config.properties";
	public static Properties property = new Properties();
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fileInput = new FileInputStream(new File(propertyFilePath));
		property.load(fileInput);
		String url=property.getProperty("ApplicationURL");
		System.out.println(url);
		String userName=property.getProperty("Username");
		System.out.println(userName);
		String password=property.getProperty("Pswd");
		System.out.println(decryptPassword(password));
	}
	
	public static String decryptPassword(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		return decodedString;		
	}

}

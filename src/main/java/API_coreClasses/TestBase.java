package API_coreClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {


	public Properties prop;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_201 = 201;
	
	
//	public static void main(String[] args) {
//		// Create object of TestNG Class
//		
//		}
	
	

	public TestBase() {

		try {
			prop = new Properties();
//			FileInputStream ip = new FileInputStream(
//					"D://Vivek//Project//Essentials_Demo//src//main//java//com//qa//config//config.properties");
			FileInputStream ip = new FileInputStream(
					"/Project/Essentials_Demo/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

package api.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.qa.requestBody.UserRequest;
import api.qa.responseBody.UserResponse;

public class PostAPITest extends API_coreClasses.TestBase {

	API_coreClasses.TestBase testBase;
	API_coreClasses.RestClient restClient;
	String serviceURL;
	String url;
	String apiURL;
	CloseableHttpResponse closeableHttpResponse;
	HashMap<String, String> headers;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		testBase = new API_coreClasses.TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = serviceURL + apiURL;

	}

	@Test
	public void postAPITest() throws ClientProtocolException, IOException {

		restClient = new API_coreClasses.RestClient();

		headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		UserRequest userrequest = new UserRequest();
		userrequest.setName("sumit");
		userrequest.setJob("ios");

		// java object to json => marshalling
		// mapper.writeValue(new
		// File("D:\\Vivek\\Project\\Essentials_Demo\\src\\main\\java\\com\\qa\\data\\user.json"),userrequest
		// );

		String userjsonreqeustbody = mapper.writeValueAsString(userrequest);
		System.out.println(userjsonreqeustbody);

		closeableHttpResponse = restClient.post(url, userjsonreqeustbody, headers);

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

		String userresponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		System.out.println(userresponseString);

//		JSONObject responsejson = new JSONObject(userjsonreqeustbody);

		// json to java object => unmarshalling
		UserResponse userresponse = mapper.readValue(userresponseString, UserResponse.class);

		Assert.assertEquals(userresponse.getName(), userrequest.getName());

		System.out.println(userresponse.getId());
		System.out.println(userresponse.getJob());
		System.out.println(userresponse.getName());
		System.out.println(userresponse.getCreatedAt());
	}
}

package api.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetAPITest extends API_coreClasses.TestBase {

	API_coreClasses.TestBase testBase;
	API_coreClasses.RestClient restClient;
	String serviceURL;
	String url;
	String apiURL;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {

		testBase = new API_coreClasses.TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = serviceURL + apiURL;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new API_coreClasses.RestClient();
		closeableHttpResponse = restClient.get(url);

		// a. status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		org.testng.Assert.assertEquals(RESPONSE_STATUS_CODE_200, statusCode);

		System.out.println(statusCode);

		// b. response String json
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		System.out.println(responseString);

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println(responseJson);

		// c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, Object> allHeader = new HashMap<String, Object>();
		for (Header header : headersArray) {

			allHeader.put(header.getName(), header.getValue());
		}

		System.out.println(allHeader);

	}

}

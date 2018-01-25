package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.TestBase;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherInfoTest extends TestBase {

	@BeforeClass
	public void setUp() {
		TestBase.init();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object testdata[][] = TestUtil.getDataFromExcelSheet(TestUtil.sheetName);
		return testdata;
		
	}
	
	@Test(dataProvider= "getData")
	public void getWeatherDetailesTest(String city, String HTTPMethos, String humidity, String temperature, String weatheerdescription, String windspeed, String winddirectiondegree) {
		// 1. define the base URL
		// http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = prop.getProperty("serviceurl");

		// 2. define the http request
		RequestSpecification httprequest = RestAssured.given();
		
		// 3.make a request or execute the request
		Response httpresponse = httprequest.request(Method.GET, "/"+ city);
		
		// get response body
		String responsebody = httpresponse.getBody().asString();
		System.out.println("Response body is:" + responsebody);

		// validate specific key in response body
		Assert.assertTrue(responsebody.contains(city), "the city Pune is present");

		// get status code and validate it
		int statuscode = httpresponse.getStatusCode();
		System.out.println("the status code is:" + statuscode);

		Assert.assertEquals(statuscode, TestUtil.RESPONSE_CODE_200);
		System.out.println("the status line is :" + httpresponse.getStatusLine());

		// 6. get headers

		Headers headers = httpresponse.getHeaders();
		System.out.println(headers);
		String contentlength = httpresponse.getHeader("content-length");
		System.out.println("the value ofcontentlength is :" + contentlength);

		String cache = httpresponse.getHeader("cache-control");
		System.out.println("the value of cache is :" + cache);
		// retrieve the body of response
		responsebody.contains("Pune");
		// get key value by using jsonpath
		JsonPath jsonpathvalue = httpresponse.jsonPath();
		String cityval = jsonpathvalue.get("City");
		System.out.println("the value of city:" + cityval);
		Assert.assertEquals(cityval, city);

		String Temperature = jsonpathvalue.get("Temperature");
		System.out.println("the value of Temperature:" + Temperature);
		Assert.assertEquals(Temperature, temperature);
		
		String Humidity = jsonpathvalue.get("Humidity");
		System.out.println("the value of Humidity:" + Humidity);

		String WindDirectionDegree = jsonpathvalue.get("WindDirectionDegree");
		System.out.println("the value of WindDirectionDegree:" + WindDirectionDegree);

	}

	

}

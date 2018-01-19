package com.qa.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {

	@Test
	public void getWeatherDetailesTest() {
		// 1. define the base URL
		// http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// 2. define the http request
		RequestSpecification httprequest = RestAssured.given();
		// 3.make a request or execute the request
		Response httpresponse = httprequest.request(Method.GET, "/Pune");
		// get response body
		String responsebody = httpresponse.getBody().asString();
		System.out.println("Response body is:" + responsebody);

		// validate specific key in response body
		Assert.assertTrue(responsebody.contains("Pune"), "the city Pune is present");

		// get status code and validate it
		int statuscode = httpresponse.getStatusCode();
		System.out.println("the status code is:" + statuscode);

		Assert.assertEquals(statuscode, 200);
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
		String city = jsonpathvalue.get("City");
		System.out.println("the value of city:" + city);

		String Temperature = jsonpathvalue.get("Temperature");
		System.out.println("the value of Temperature:" + Temperature);

		String Humidity = jsonpathvalue.get("Humidity");
		System.out.println("the value of Humidity:" + Humidity);

		String WindDirectionDegree = jsonpathvalue.get("WindDirectionDegree");
		System.out.println("the value of WindDirectionDegree:" + WindDirectionDegree);

	}

}

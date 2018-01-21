package com.qa.rest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateCustomerPostCall {

	@Test
	public void createCustomerTest() {
		// 1. define baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";

		// 2.define HTTP request
		RequestSpecification httpRequest = RestAssured.given();
		// 3.create JSON object with all the fields
		JSONObject requestJson = new JSONObject();
		requestJson.put("FirstName", "EPSMichael01");
		requestJson.put("LastName", "EPSRama21");
		requestJson.put("UserName", "epmmicrama31");
		requestJson.put("Password", "eprmich5675");
		requestJson.put("Email", "eprrama12@gmail.com");

		// 4. add header
		httpRequest.header("Content-Type", "application/javascript");
		// 5. add the json payload to the body of request
		httpRequest.body(requestJson.toString());

		// 6.Post request and get the reponse
		Response httpResponse = httpRequest.post();
		// 7. get response body
		String responsebody = httpResponse.getBody().asString();
		System.out.println("response body is:" + responsebody);
		// 8. get the status code and validate it

		System.out.println(httpResponse.getStatusLine());
		int statuscode = httpResponse.getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, 201);
		// 9. get the headers
		Headers headers = httpResponse.getHeaders();
		System.out.println(headers);

		String contentheader = httpResponse.getHeader("Content-Type");
		System.out.println("the contentheader is :" + contentheader);

		String contentLength = httpResponse.getHeader("Content-Length");
		System.out.println("the content  length is:" + contentLength);

	}

}

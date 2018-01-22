package com.qa.rest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PostCallWithJavaObjects {

	@Test
	public void createCustomerTest() {
		// 1. define baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/register";

		// 2.define HTTP request
		RequestSpecification httpRequest = RestAssured.given();
		// 3.create JSON object with all the fields
		JSONObject requestJson = new JSONObject();
		requestJson.put("FirstName", "EPSMichael011");
		requestJson.put("LastName", "EPSRama211");
		requestJson.put("UserName", "epmmicrama311");
		requestJson.put("Password", "eprmich56751");
		requestJson.put("Email", "eprrama112@gmail.com");

		// 4. add header
		httpRequest.header("Content-Type", "application/javascript");
		// 5. add the json payload to the body of request
		httpRequest.body(requestJson.toString());

		// 6.Post request and get the reponse
		Response httpResponse = httpRequest.post();
		// 7. get response body
		String responsebody = httpResponse.getBody().asString();
		System.out.println("response body is:" + responsebody);

		// Deserialization of the response into customerResponse class

		if (httpResponse.statusCode() == 201) {
			CustomerResponseSuccesss customerResponse = httpResponse.as(CustomerResponseSuccesss.class);
			System.out.println(customerResponse.SuccessCode);
			System.out.println(customerResponse.Message);

		} else if (httpResponse.statusCode() == 200) {
			CustomerResponseFailure customerResponse = httpResponse.as(CustomerResponseFailure.class);

			System.out.println(customerResponse.FaultId);
			System.out.println(customerResponse.fault);
			
			Assert.assertEquals(customerResponse.FaultId, "User already exists");
			Assert.assertEquals(customerResponse.fault, "FAULT_USER_ALREADY_EXISTS");
			
			
		}

	}

}

package org.com.projectname.testcases;

import org.com.projectname.testbase.TestBase;
import org.com.projectname.utilities.RestUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC_003_Put_SingleEmployeeDetail extends TestBase {

//	String empname=RestUtils.getName();
//	String empage=RestUtils.getAge();
//	String empsalary=RestUtils.getSalary();
//	
	@BeforeClass
	public void putSingleEmployeeDetail() throws InterruptedException {
		
		logger.info("<--------Starting TC_004_Put_SingleEmployeeDetail------->");
		RestAssured.baseURI="http://localhost:3030";
		httprequest = RestAssured.given();
		
		JSONObject requestparams= new JSONObject();
		
		requestparams.put("name", "Top Raman");
		requestparams.put("type", "Snacks");
		requestparams.put("price", 30);
		requestparams.put("upc", "TopRamanupc");
		requestparams.put("shipping", 2);
		requestparams.put("description", "It is American Style Noodles.");
		requestparams.put("manufacturer", "ITC");
		requestparams.put("model", "ITR23");
		requestparams.put("url", "htrp://www.TopRaman.com/Noodles");
		requestparams.put("image", "http://www.TopRaman.com/Images=Noodles");
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestparams.toString());
		
	    response = httprequest.request(Method.PUT, "/products/"+productId);
		Thread.sleep(7000);
	}
	
	@Test
	public void checkResponseBody() {
		
		logger.info("<------checking Response Body------>");
		String responsebody = response.getBody().asString();
		logger.info("The Response Body is ==>"+responsebody);
		Assert.assertTrue(responsebody.contains("Top Raman"));
		Assert.assertTrue(responsebody.contains("30"));
		Assert.assertTrue(responsebody.contains("It is American Style Noodles."));
	}
	

	@Test
	public void checkStatusCode() {
		logger.info("<------Checking Status Code------->");
		int statuscode = response.getStatusCode();
		logger.info("The Status Code is ==>"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void checkResponsTime() {
		logger.info("<-------Checking Response Time------->");
		long responsetime = response.getTime();
		logger.info("The Response Time is ==>"+responsetime);
		if(responsetime<2000) {
			logger.warn("The Response Time is less than 2000");
		}
		Assert.assertTrue(responsetime<10000);
	}
	
	@Test
	public void checkContentLength() {
		logger.info("<-------Checking Content Type--------->");
		String contentLength = response.header("Content-Length");
		logger.info("The Content Length is ==>"+contentLength);
		if(Integer.parseInt(contentLength)<100) {
			logger.warn("The Content Length is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength)<700);
	}
	
	@Test
	public void checkConnectionType() {
		logger.info("<------Checking Connection Type-------->");
		 String connectionType = response.header("Connection");
		logger.info("The connection Type is ==>"+connectionType);
		Assert.assertEquals(connectionType, "keep-alive");
	}
	
	@Test
	public void checkContentType() {
		logger.info("<------Checking Content Type------->");
		String contentType = response.header("Content-Type");
		logger.info("The ServerType is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	@AfterClass
	public void tearDown() {
		logger.info("<------Ending TC_004_Put_SingleEmployeeDeta-------->");
	}
	
}

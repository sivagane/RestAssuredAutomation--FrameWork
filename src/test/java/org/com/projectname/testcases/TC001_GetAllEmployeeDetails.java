package org.com.projectname.testcases;

import org.com.projectname.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmployeeDetails extends TestBase{

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		
		logger.info("<-------Starting TC001_GetAllEmployeeDetails----->");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httprequest=RestAssured.given();
		response=httprequest.request(Method.GET, "/employees");
		Thread.sleep(5000);
	}
	
	@Test
	public void checkResponseBody() {
		
		logger.info("<----Checking Response Body----->");
		String responsebody = response.getBody().asString();
		logger.info("The Response Body is ==>"+responsebody);
		Assert.assertTrue(responsebody!=null);
		
	}
	@Test
	public void checkStatusCode() {
		logger.info("<-----Checking Status Code----->");
		int statuscode = response.getStatusCode();
		logger.info("The Status Code is ==>"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	@Test
	public void checkResponseTime() {
		logger.info("<----Checking Response Time---->");
		long responsetime = response.getTime();
		logger.info("The Response Time is ==>"+responsetime);
		if(responsetime>2000)
			logger.warn("Respose Time is greater than 2000");
		
		Assert.assertTrue(responsetime<=10000);
	}
	

	@Test
	public void checkStatusLine() {
		logger.info("<-----Checking Status Line------>");
		String statusline = response.getStatusLine();
		logger.info("The Status Line is ==>"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void contentType() {
		logger.info("<-------Checking Content Type------->");
		String contentType = response.header("Content-Type");
		logger.info("The Content Type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	public void checkServerType() {
		logger.info("<--------Checking Server Type------>");
		String serverType = response.header("Server");
		logger.info("The Server Type is ==>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	public void checkContentEncoding() {
		logger.info("<-------Checking Content Encoding----->");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("The Content Encoding is ==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	public void checkContentLength() {
		logger.info("<------Checking Content Length------>");
		String contentLength = response.header("Content-Length");
		logger.info("The Content Length is ==>"+contentLength);
		if(Integer.parseInt(contentLength)<100) 
			logger.warn("Content Length is less than 100");
			Assert.assertTrue(Integer.parseInt(contentLength)<700);
		}
		
	@Test
	public void checkCookies() {
		logger.info("<-------Checking Cookies------->");
		String cookie = response.getCookie("PHPSESSID");
		logger.info("The Cookies are ==>"+cookie);
	}
	
	@Test
	public void tearDown() {
		logger.info("<---------Ending TC001_GetAllEmployeeDetails--------->");
	}

	
}

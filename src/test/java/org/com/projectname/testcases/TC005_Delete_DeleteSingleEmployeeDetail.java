package org.com.projectname.testcases;

import org.com.projectname.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC005_Delete_DeleteSingleEmployeeDetail extends TestBase {

	@BeforeClass
	public void deleteSingleEmployeeDetail() throws InterruptedException {
		RestAssured.baseURI="http://localhost:3030/";
		httprequest=RestAssured.given();
		response=httprequest.request(Method.GET, "/employees");
		
		JsonPath JsonPathEvaluator= response.jsonPath();
		String empId = JsonPathEvaluator.get("[0].id");
		
        response = httprequest.request(Method.DELETE, "/products/"+productId);
		Thread.sleep(3000);
	}
	
	@Test
	public void CheckResponseBody() {
		logger.info("<---------Checking Response Body--------->");
		String responsebody = response.getBody().asString();
		logger.info("The Response Body is ==>"+responsebody);
		Assert.assertEquals(responsebody.contains("successfully! deleted Records"), true);
	}
	
	@Test
	public void CheckStatusCode() {
		logger.info("<-------Checking Status Code------------->");
		int statuscode = response.getStatusCode();
		logger.info("The Status Code is ==>"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void CheckResponseTime() {
		logger.info("<------Checking Response Time---------->");
		long responsetime = response.getTime();
		logger.info("The Response Time is ==>"+responsetime);
		if(responsetime>2000) {
			logger.warn("The Resoponse Time is Greater Than 2000");
		}
		Assert.assertTrue(responsetime<10000);
	}
	
	@Test
	public void CheckStatusLine() {
		logger.info("<------Checkig Status Line-------->");
		String statusline = response.getStatusLine();
		logger.info("The Status Line is ==>"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void checkContentType() {
		logger.info("<------Checking Content Type-------->");
		String contentType = response.header("Content-Type");
		logger.info("The Content Type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
//	@Test
//	public void checkServerType() {
//		logger.info("<--------Checking Server Type------>");
//		String serverType = response.header("Server");
//		logger.info("The Server Type is ==>"+serverType );
//		Assert.assertEquals(serverType, "nginx/1.16.0");
//	}
}

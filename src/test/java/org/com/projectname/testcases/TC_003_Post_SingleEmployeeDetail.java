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

public class TC_003_Post_SingleEmployeeDetail extends TestBase{


	@BeforeClass
	public void postSingleEmployee() throws InterruptedException {
		logger.info("<---------Starting TC_003_Post_SingleEmployeeDetail------->");
		RestAssured.baseURI="http://localhost:3030";
		httprequest = RestAssured.given();
		JSONObject requestparams= new JSONObject();
		
		requestparams.put("name", "Yamaha FZ");
		requestparams.put("type", "Bike");
		requestparams.put("price", 90000);
		requestparams.put("upc", "Yamahaupc");
		requestparams.put("shipping", 100);
	    requestparams.put("description", "Yamaha Bike with Dual disc Brake, speed upto 160kmp and Guaranteed mileage of 50*");
		requestparams.put("manufacturer", "Yamaha-Japan");
		requestparams.put("model", "160CC");
		requestparams.put("url", "http://www.Yamaha.com/Bikes-FZ");
		requestparams.put("image", "http://www.Yamaha.com/Image=YamahaFZ");
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestparams.toString());
		
	    response = httprequest.request(Method.POST, "/products");
	    Thread.sleep(5000);
		}
	
	@Test
	public void checkResponseBody() {
		logger.info("<-------Checking Response Body-------->");
		String responsebody = response.getBody().asString();
		logger.info("The Response Body is ==>"+responsebody);
		Assert.assertEquals(responsebody.contains("Yamaha FZ"), true);
		Assert.assertEquals(responsebody.contains("Bike"), true);
		Assert.assertEquals(responsebody.contains("160CC"), true);
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
	public void checkContentType() {
		logger.info("<------Checking Content Type-------->");
		String contentType = response.header("Content-Type");
		logger.info("The Content Type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
//	@Test
//	public void checkServerType() {
//		logger.info("<------Checking Server Type------->");
//		String serverType = response.header("Server");
//		logger.info("The ServerType is ==>"+serverType);
//		Assert.assertEquals(serverType, "nginx/1.16.0");
//	}
	@AfterClass
	public void tearDown() {
		logger.info("<------Ending TC_003_Post_SingleEmployeeDetil-------->");
	}
}

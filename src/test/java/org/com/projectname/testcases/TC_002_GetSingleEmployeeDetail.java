package org.com.projectname.testcases;

import org.com.projectname.testbase.TestBase;
import org.com.projectname.utilities.RestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC_002_GetSingleEmployeeDetail extends TestBase {

	
			@BeforeClass
			public void getSingleEmployee() throws InterruptedException {
				logger.info("<------Stating TC_002_GetSingleEmployeeDetail------>");
		    RestAssured.baseURI="http://localhost:3030";
		    httprequest = RestAssured.given();
		    response= httprequest.request(Method.GET, "/products/"+productId);
		    Thread.sleep(3000);
	}
			
			@Test
			public void checkResponeBody() {
				logger.info("<-------Checking Response Body------->");
				String responsebody = response.getBody().asString();
				logger.info("The Response Body is ==>"+responsebody);
				Assert.assertTrue(responsebody!=null);
			}
			
			@Test
			public void checkStatusCode() {
				logger.info("<-------Checking Status Code----->");
				int statuscode = response.getStatusCode();
			    logger.info("The Status Code is ==>"+statuscode);
			    Assert.assertEquals(statuscode, 200);
			}
			
			@Test
			public void checkResponsTime() {
				logger.info("<--------Checking Response Time------->");
				long responsetime = response.getTime();
				logger.info("The Response Time is ==>"+responsetime);
				if(responsetime>2000) {
				logger.warn("The Response Time is greater than 2000");
				}
				Assert.assertTrue(responsetime<=10000);
			}
			
			@Test
			public void checkStatusLine() {
				logger.info("<-------Checking Status Line");
				String statusline = response.getStatusLine();
				logger.info("The Status Line is ==>"+statusline);
				Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
			}
			
			@Test
			public void checkContentType() {
				logger.info("<--------Checking Content Type------>");
				String contentType = response.header("Content-Type");
				logger.info("The Content Type is ==>"+contentType);
			    Assert.assertEquals(contentType, "application/json; charset=utf-8");
			}
//			
//			@Test
//			public void checkServerType() {
//				logger.info("<-------Checkig Server Type------>");
//				String serverType = response.header("Server");
//				logger.info("The Server Type is ==>"+serverType);
//				Assert.assertEquals(serverType, "nginx/1.16.0");
		//	}
			
			@AfterClass
			public void tearDown() {
				logger.info("<------Ending TC_003_Post_SingleEmployeeDetil-------->");
			}
			
}

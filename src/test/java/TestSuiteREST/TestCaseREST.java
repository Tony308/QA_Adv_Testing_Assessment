package TestSuiteREST;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.Main.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import org.json.JSONObject;

public class TestCaseREST {
	
	RequestSpecification request;
	Response response;
	ValidatableResponse json;
	
	ExtentReports report = new ExtentReports(Constant.filepath + Constant.restReport,true);
	ExtentTest test;
	JSONObject params;
	JSONObject petParams;

		
	@Before
	public void setup() {

		request = given().contentType(ContentType.JSON);
		params = new JSONObject();
		petParams = new JSONObject();
	
	}
	
	@After
	public void teardown() {
		report.endTest(test);
		report.flush();
	}
	
	@Test
	public void getRequest() {

		test = report.startTest("Get Request Test.");
		
		test.log(LogStatus.INFO, "Sending request.");
		
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners");
		
		test.log(LogStatus.INFO, "Response code: " + response.getStatusCode());

		if (response.getStatusCode() != 200) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(200, response.getStatusCode());
		
		test.log(LogStatus.PASS, "Test Passed.");
	}
	@Test
	public void postRequest() {
	
		test = report.startTest("Post Request Test.");
		params.put("id", "13");
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Tony");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951123");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Sending request.");
		response = request.post("http://10.0.10.10:9966/petclinic/api/owners");

		if (response.getStatusCode() != 201) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(201, response.getStatusCode());
	}
	
	@Test
	public void getBySurname() {
		

		test = report.startTest("Get by surname request.");
		
		test.log(LogStatus.INFO, "Sending request.");
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners/*/lastname/Davis");

		if (response.getStatusCode() != 200) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(200,response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Test
	@Ignore
	public void deleteRequest() {

		test = report.startTest("Delete Request Test");
		
		response = request.delete("http://10.0.10.10:9966/petclinic/api/owners/13");
		
		if (response.getStatusCode() != 204) {
			if (response.getStatusCode() == 404) {
				test.log(LogStatus.FAIL, "Test failed. Record with ID 13 hasn't been created yet.");
			}
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Test
	public void putRequest() {
		test = report.startTest("Put Request Test.");
		
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Tony");
		params.put("id",  "1");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951707");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Sending request.");
		response = request.put("http://10.0.10.10:9966/petclinic/api/owners/1");

		if (response.getStatusCode() != 204) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
		
	}

	
}

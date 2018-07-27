package TestSuiteREST;

import org.junit.After;
import org.junit.Before;
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
	
	ExtentReports report;
	ExtentTest test;
	JSONObject params;
	
		
	@Before
	public void setup() {

		request = given().contentType(ContentType.JSON);
		params = new JSONObject();
		report = new ExtentReports(Constant.filepath + Constant.restReport,false);
		report = new ExtentReports("C:\\Users\\Admin\\eclipse-workspace\\Adv_Testing_Assessment\\src\\CucumberReport.html", false);
	}
	
	@After
	public void teardown() {
		report.endTest(test);
		report.flush();
	}
	
	@Test
	public void getRequest() {

		test = report.startTest("Get Request Test.");
		test.log(LogStatus.INFO, "Test Started.");
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
		
		test.log(LogStatus.INFO, "Test Started.");
		//an id is required despite not needed to post.
		params.put("id", "1");
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Tony");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951123");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Sending request.");
		response = request.post("http://10.0.10.10:9966/petclinic/api/owners");
		test.log(LogStatus.INFO, "Response code: "+ response.getStatusCode());
		if (response.getStatusCode() != 201) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(201, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Test
	public void getBySurname() {
		test = report.startTest("Get by surname request.");
		test.log(LogStatus.INFO, "Test Started.");
		test.log(LogStatus.INFO, "Sending request.");
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners/*/lastname/Davis");
		test.log(LogStatus.INFO, "Response code: " + response.getStatusCode());
		if (response.getStatusCode() != 200) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(200,response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Test
	public void deleteRequest() {
		int id = 19;
		test = report.startTest("Delete Request Test");
		test.log(LogStatus.INFO, "Sending request");
		test.log(LogStatus.INFO, "Deleting record at id " + id);
		response = request.delete("http://10.0.10.10:9966/petclinic/api/owners/" + id);
		
		test.log(LogStatus.INFO, "Response code: " + response.getStatusCode());
		
		if (response.getStatusCode() != 204) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Test
	public void putRequest() {
		int id = 1;
		test = report.startTest("Put Request Test.");
		test.log(LogStatus.INFO, "Test Started.");
		
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Cheesey");
		params.put("id",  "1");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951707");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Sending request.");
		test.log(LogStatus.INFO, "Updating record at id "+ id);
		response = request.put("http://10.0.10.10:9966/petclinic/api/owners/" + id );
		
		test.log(LogStatus.INFO, "Response code: "+ response.getStatusCode());
		if (response.getStatusCode() != 204) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
		
	}

	
}

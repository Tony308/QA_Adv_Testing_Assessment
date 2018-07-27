package CucumberTestSuite;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import com.qa.Main.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import static org.junit.Assert.*;

public class Steps {
	
	RequestSpecification request;
	Response response;
	ValidatableResponse json;

	ExtentReports report ;
	ExtentTest test;
	JSONObject params;

	@Before
	public void setup() {

		params = new JSONObject();
		report = new ExtentReports(Constant.filepath + Constant.cucumberReport, false);
		test = report.startTest("Cucumber Test: User Story");
	}
	
	@After
	public void teardown() {
		report.endTest(test);
		report.flush();
		
	}
	
	@Given("^The website works/exists$")
	public void the_website_works_exists() {

		test.log(LogStatus.INFO, "Test Started.");
		
		request = given().contentType(ContentType.JSON);
		
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners");
		assertEquals(200, response.getStatusCode());
		test.log(LogStatus.PASS, "Website exists/works.");

	}

	@When("^I send a get request$")
	public void i_send_a_get_request() {
		test.log(LogStatus.INFO, "Retrieving list of owners");
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners");
	}

	@Then("^I should receive a list of owners$")
	public void i_should_receive_a_list_of_owners() {
		
		
		test.log(LogStatus.INFO, "2XX code means it's successful. Response code: " + response.getStatusCode());
		if (response.getStatusCode() != 200) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(200, response.getStatusCode());
		
		test.log(LogStatus.PASS, "Test Passed.");
	}

	@When("^I search a specific owner$")
	public void i_search_a_specific_owner() {
		test.log(LogStatus.INFO, "Searching for Davis");
		response = request.when().get("http://10.0.10.10:9966/petclinic/api/owners/*/lastname/Davis");
		
	}

	@Then("^I should receive a record of that owner$")
	public void i_should_receive_a_record_of_that_owner() {
		test.log(LogStatus.INFO, "Verifying search");
		test.log(LogStatus.INFO, "2XX code means it's successful. Response code: " + response.getStatusCode());
		if (response.getStatusCode() != 200) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(200,response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}

	@When("^I create a new owner$")
	public void i_create_a_new_owner() {
		test.log(LogStatus.INFO, "Creating new owner.");
		
		//an id is required despite not needed to post.
		params.put("id", "1");
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Tony");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951123");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Submitting ... ");
		response = request.post("http://10.0.10.10:9966/petclinic/api/owners");

	}

	@Then("^See that new owner has been added$")
	public void see_that_new_owner_has_been_added() {
		test.log(LogStatus.INFO, "Verifying submit ... ");
		test.log(LogStatus.INFO, "2XX code means it's successful. Response code: " + response.getStatusCode());
		
		if (response.getStatusCode() != 201) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(201, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}
	
	@Given("^An owner exists$")
	public void an_owner_exists() {
		test.log(LogStatus.INFO , "Cannot verify if user exists!");

	}

	@When("^I delete an owner$")
	public void i_delete_an_owner() {
		int id = 19;
		
		test.log(LogStatus.INFO, "Deleting ... ");
		response = request.delete("http://10.0.10.10:9966/petclinic/api/owners/" + id);
		

	}

	@Then("^Check the owner has been deleted$")
	public void check_the_owner_has_been_deleted() {
		test.log(LogStatus.INFO, "2XX code means it's successful. Response code: " + response.getStatusCode());
		
		if (response.getStatusCode() != 204) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}

	@When("^I update an owner$")
	public void i_update_an_owner() {
		int id = 1;
		
		test.log(LogStatus.INFO, "Test Started.");
		
		params.put("address", "something");
		params.put("city", "London");
		params.put("firstName", "Cheesey");
		params.put("id",  "1");
		params.put("lastName", "Huang");
		params.put("telephone", "07751951707");
		request.body(params.toString());
		
		test.log(LogStatus.INFO, "Updating record at id "+ id);
		response = request.put("http://10.0.10.10:9966/petclinic/api/owners/" + id );
		

	}

	@Then("^the owner should update$")
	public void the_owner_should_update() {
		
		test.log(LogStatus.INFO, "2XX code means it's successful. Response code: " + response.getStatusCode());

		if (response.getStatusCode() != 204) {
			test.log(LogStatus.FAIL, "Test failed. Response Code: " + Constant.giveResponse(response.getStatusCode()));
		}
		assertEquals(204, response.getStatusCode());
		test.log(LogStatus.PASS, "Test passed.");
	}

}

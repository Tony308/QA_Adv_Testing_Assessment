package com.qa.Main;

public class Constant {
	public static final String filepath = "C:\\Users\\Admin\\eclipse-workspace\\Adv_Testing_Assessment\\src\\";
	
	public static final String chromedriver = "chromedriver.exe";
	
	public static final String seleniumReport = "SeleniumReport.html";
	
	public static final String restReport = "RestReport.html";
	
	public static final String url = "http://10.0.10.10:4200/petclinic/";
	
	public static String giveResponse(int response) {
		
		if (response == 201) {
			return "201 - `Created`";	
		} else if (response == 200) {
			return "200 - OK";
		} else if (response == 401) {
			return "401 - Unauthorized";
		} else if (response == 403) {
			return "403 - Forbidden";
		} else if (response == 404) {
			return "404 - Not Found";
		}  else if (response == 204) {
			return "204 - No Content";
		} else if (response == 400) {
			return "400 - Failed";
		}
		return "" + response;
	}
	
}

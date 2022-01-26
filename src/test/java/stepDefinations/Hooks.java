package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	/*
	 * reason we used @deleteplace in @Before annotation,we have 2 scenarios 
	 * 2 scenario Deleteplace  in feature file is depends on 1st scenario to get placeid
	 * if we notice testrunner file near tags we provide only @deleteplace ,for that reason before @deleteAPI scenario runs it runs below logic to get placeid & then it executes  deleteapi scenario
	 */
	
	
	@Before("@Deleteplace")
	public void BeforeScenario() throws IOException {
		
		StepDefination sd = new StepDefination();
		
		if(StepDefination.place_id==null) {
			
			sd.add_place_playload("sss", "adpeta", "asia");
			sd.user_calls_with_http_request("AddPlaceAPI", "post");
			sd.verify_place_id_created_is_maps_to_using("sss", "getPlaceAPI");
		}
		
	}

}

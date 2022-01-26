package stepDefinations;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	public RequestSpecification res;
	public ResponseSpecification resspec;
	public Response response;
	
	
	static  String place_id;
	
	//create obj for testdatabuild class to access playload methods
	TestDataBuild data = new TestDataBuild();


	@Given("Add place playload {string} {string} {string}")
	public void add_place_playload(String name, String language, String address) throws IOException {
		
		res=given().spec(requestSpecification()).body(data.adddPlacePlayLoad(name,language,address));
		
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		
	}

      
		       @When("user calls {string} with {string} http request")
		       public void user_calls_with_http_request(String resource, String httpMethod) {
		    	                             //enum class APIResources  constructor will be called first with the value of resource we passed
		   		APIResources resourceAPI=	APIResources.valueOf(resource);
		   		
		   		System.out.println(resourceAPI.getResource());
		   		
		   		if(httpMethod.equalsIgnoreCase("POST"))
		   		       response =res.when().post(resourceAPI.getResource());
		   		else if(httpMethod.equalsIgnoreCase("GET"))
		   		{
		   			response =res.when().get(resourceAPI.getResource());
		   		}
	  
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
	//assertEquals is a assetion of junit
	 assertEquals(response.getStatusCode(), 200);
	}
	
	  
	@Then("{string} in response body is {string}")
	                                       //keyvalue is -->status and expected values is wheat we sent from feature file
	public void in_response_body_is(String keyValue, String Expectedvalue) {

	
	
		          //assertEquals(getJsonPath(response, keyValue), Expectedvalue);
		                assertEquals(Expectedvalue,getJsonPath(response, keyValue));
	}

	@Then("verify place_id created is maps to {string} using {string}")
	public void verify_place_id_created_is_maps_to_using(String expectedName, String resource) throws IOException {
		
		     place_id=getJsonPath(response, "place_id");
            System.out.println("place_id is --->"+place_id);
		
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
	              user_calls_with_http_request(resource, "GET");
	          
	              String actualName=getJsonPath(response, "name");
	              System.out.println("actualName is --->"+actualName);
				
	              assertEquals(expectedName, actualName);
	
	}
	
	@Given("DeletePlace Playload")
	public void delete_place_playload() throws IOException {
		
		String deletejsonplayload=data.deletePlacePlayLoad(place_id);
		 System.out.println("deletejsonplayload is --->"+deletejsonplayload);
	
		res=given().spec(requestSpecification()).body(data.deletePlacePlayLoad(place_id));
		
		
	}



}

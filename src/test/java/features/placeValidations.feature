Feature: Validating place API's


@Addplace 
Scenario Outline: Verify if place is being successfully added by AddPlace API
        Given Add place playload "<name>" "<language>" "<address>"
        When user calls "AddPlaceAPI" with "post" http request
        Then the API call got success with status code 200
        And "status" in response body is "OK"
        And "scope" in response body is "APP"
        And verify place_id created is maps to "<name>" using "getPlaceAPI"
        
		
		Examples:
		| name       | language | address        |
		| skuchipudi1 | telugu_1  | Annadeverapeta1 |
#		| skuchipudi2 | telugu _2 | Annadeverapeta2 |
		
		
@Deleteplace	
Scenario: verify if delete place functionality is working
          Given DeletePlace Playload
          When user calls "deletePlaceAPI" with "post" http request
          Then the API call got success with status code 200
          And "status" in response body is "OK"

           
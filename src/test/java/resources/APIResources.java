package resources;


//enum is a special class in java which has collection of constants and methods

public enum APIResources {
	
	
	     // in enum class methods define like below 
	AddPlaceAPI("/maps/api/place/add/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	getPlaceAPI("/maps/api/place/get/json");
	
	private String resource;  //we declared this variable to access value in all class
	
	//enum constructor --enum class should have complusory constructor
	
	APIResources(String resource) //when we call enum class,first constructor will execute & gets the above method value & saves it in same resource variable
	
	{
		
		this.resource = resource;
	}
	
	
	public String getResource() {
		return resource;
	}

	
}

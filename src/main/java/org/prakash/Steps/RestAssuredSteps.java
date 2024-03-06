package org.prakash.Steps;

import java.io.IOException;

import org.prakash.APIMethods.APIMethodImplementations;
//import org.prakash.common.FileReadandWrite;
import org.prakash.common.FileReadandWrite;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestAssuredSteps extends APIMethodImplementations {
	public String place_id = "";
	
	
	APIMethodImplementations implClass = new APIMethodImplementations();
	
	@Given("User tries to add a place {string} {string} {string} {string} {string}")
	public void user_tries_to_add_a_place(String name, String PhoneNumber, String address, String website, String language) throws Exception {
		setUpconfig();
		place_id=implClass.addPlaceApi(name, PhoneNumber, address, website, language);
		FileReadandWrite.writeTextFile(place_id);
		System.out.println("The id for place is :" +place_id);
	}
	@When("User tries to get the address added {string} {string} {string} {string} {string}")
	public void user_tries_to_get_the_address_added(String name, String PhoneNumber, String address, String website, String language) throws IOException {
		String PlaceID = FileReadandWrite.readTextFile();
		System.out.println("the place id is from get. : " +PlaceID);
	    implClass.getPlaceApi(PlaceID, name, PhoneNumber, address, website, language);
	}
	@And("User update the address due to correction")
	public void user_update_the_address_due_to_correction() {
	   implClass.updatePlaceApi(place_id);
	}
	@Then("User deletes the added address.")
	public void user_deletes_the_added_address() {
		implClass.deletePlaceApi(place_id);
	}

}

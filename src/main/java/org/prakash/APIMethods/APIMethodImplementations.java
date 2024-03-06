package org.prakash.APIMethods;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.prakash.common.APIEndPoints;
import org.prakash.common.Config;
import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

public class APIMethodImplementations extends Config {
	
	public String baseURL = "";
			
			//properties.getProperty("BaseURL");
	
	public String addPlaceApi(String name, String PhoneNumber, String address, String website, String language) {
		
		baseURL = properties.getProperty("BaseURL");
		JSONObject location = new JSONObject();
		location.put("lat", "-23.4545");
		location.put("lng", "23.6787");
		
		JSONArray types = new JSONArray();
		types.add("Central Park");
		types.add("Penthouse");
		
		JSONObject reqBody = new JSONObject();
		reqBody.put("location", location);
		reqBody.put("accuracy", "65");
		reqBody.put("name", name);
		reqBody.put("phone_number", PhoneNumber);
		reqBody.put("address", address);
		reqBody.put("types", types);
		reqBody.put("website", website);
		reqBody.put("language", language);
		
		
		Response response = given().log().all().accept(ContentType.JSON)
				.contentType("application/json")
				.queryParam("key", "qaclick123").and().body(reqBody.toString())
				.post(baseURL+APIEndPoints.addAddress).thenReturn();
		System.out.println("The response is : " + response.asPrettyString());
		
		Assert.assertTrue(response.getStatusCode() == 200);
		Assert.assertTrue(response.jsonPath().getString("status").equals("OK"));
		Assert.assertTrue(response.jsonPath().getString("scope").equals("APP"));
		Assert.assertFalse(response.jsonPath().getString("place_id").isEmpty());
		Assert.assertFalse(response.jsonPath().getString("reference").isEmpty());
		Assert.assertFalse(response.jsonPath().getString("id").isEmpty());
		
		String place_id = response.jsonPath().getString("place_id");
		System.out.println("The place id is  :" +place_id);
		
	return place_id;
	}
	
	public void getPlaceApi(String placeID, String name, String phoneNumber, String address, String website, String language) {
		
		Response response = given().log().all().accept(ContentType.JSON).contentType("application/json")
				.queryParam("key", "qaclick123").queryParam("place_id", placeID).and().get(baseURL+APIEndPoints.getAddress)
				.thenReturn();
		
		System.out.println("The get api response is :" +response.asPrettyString());
		Assert.assertTrue(response.getStatusCode() == 200);
		Assert.assertFalse(response.jsonPath().getString("location.latitude").isEmpty());
		Assert.assertFalse(response.jsonPath().getString("location.longitude").isEmpty());
		Assert.assertTrue(response.jsonPath().getString("accuracy").matches("\\d{2}"));
		Assert.assertTrue(response.jsonPath().getString("name").equals(name));
		Assert.assertTrue(response.jsonPath().getString("phone_number").equals(phoneNumber));
		Assert.assertTrue(response.jsonPath().getString("address").equals(address));
		Assert.assertTrue(response.jsonPath().getString("website").equals(website));
		Assert.assertTrue(response.jsonPath().getString("language").equals(language));
		
	}
	
	public void updatePlaceApi(String placeID) {
		String successMsg = "Address successfully updated";
		JSONObject putReq = new JSONObject();
		putReq.put("place_id", placeID);
		putReq.put("address", "New Madonna Street");
		putReq.put("key", "qaclick123");
		
		
		Response response = given().log().all().accept(ContentType.JSON)
				.contentType("application/json").queryParam("key", "qaclick123").queryParam("place_id", placeID).and().body(putReq.toString())
				.put(baseURL+APIEndPoints.updateAddress).thenReturn();
		System.out.println("The PUT REsponse is :" + response.asPrettyString());
		Assert.assertTrue(response.getStatusCode() == 200);
		Assert.assertEquals(response.jsonPath().getString("msg"), successMsg);
		Assert.assertTrue(response.jsonPath().getString("msg").equalsIgnoreCase("address successfully UpdaTED"));
		
	}
	
	public void deletePlaceApi(String placeID) {
		
		JSONObject delReq = new JSONObject();
		delReq.put("place_id", placeID);
		
		Response response = given().log().all().accept(ContentType.JSON)
				.contentType("application/json").queryParam("key", "qaclick123").and().body(delReq.toString())
				.put(baseURL+APIEndPoints.delAddress).thenReturn();
		System.out.println("The Delete REsponse is :" + response.asPrettyString());
		Assert.assertTrue(response.getStatusCode() == 200);
		Assert.assertTrue(response.jsonPath().getString("status").equalsIgnoreCase("OK"));
		
	}

}

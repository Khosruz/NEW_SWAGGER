package com.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class InteractiveDocuments {

	// Returns all the videos games in the DB
	@Test(priority = 1, groups = "Get Methods")
	public void test_get01() {
		System.out.println("\n============================================================");
		System.out.println("\n======================= GET REQUEST ========================\n");
		Response response =
		given().
			contentType("application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
		when().
			get("http://localhost:8080/app/videogames").
		then().
			statusCode(200).
			log().all().
			extract().response();
		int curentStatuscode = response.statusCode();
		AssertJUnit.assertEquals(curentStatuscode, 200);
		System.err.println("The Response Code is ("+curentStatuscode+")");
	}
	// Add a new video game to the DB
	@Test(priority = 2, groups = "POST Methods")
	public void test_Post() {
		System.out.println("\n============================================================");
		System.out.println( "====================== POST REQUEST ========================");
		HashMap data = new HashMap();
		data.put("id", "102");
		data.put("name", "Erthogul");
		data.put("releaseDate", "2021-02-02T00:46:17.668Z");
		data.put("reviewScore", "9");
		data.put("category", "History");
		data.put("rating", "10");
		
		Response response =
		given().
			contentType("application/json").
			contentType(ContentType.JSON).
			body(data).
		when().
			post("http://localhost:8080/app/videogames").
		then().
			statusCode(200).
			log().all().
			extract().response();
		int curentStatuscode = response.statusCode();
		AssertJUnit.assertEquals(curentStatuscode, 200);
		String jsonString = response.asString();
		AssertJUnit.assertEquals(jsonString.contains("Record Added Successfully"), true);
		System.err.println("The Response Code is ("+curentStatuscode+")");
	}
	// Returns all the newly added videos games in the DB
	@Test(priority = 3, groups = "Get Methods")
	public void test_get02() {
		System.out.println("\n============================================================");
		System.out.println("======================= GET REQUEST ========================\n");
		System.out.println("The Header Type Is: ");
		Response response =
		given().
			contentType("application/json").
			
		when().
			get("http://localhost:8080/app/videogames/102").
		then().
			body("VideoGame.id", equalTo("102")).
			body("VideoGame.name", equalTo("Erthogul")).
			statusCode(200).
			log().all().
			extract().response();
		int curentStatuscode = response.statusCode();
		AssertJUnit.assertEquals(curentStatuscode, 200);
		System.err.println("The Response Code is ("+curentStatuscode+")");
	}
	// Update an existing video game in the DB by specifying a new body
	@Test(priority = 4, groups = "PUT Methods")
	public void test_PUT() {
		System.out.println("\n============================================================");
		System.out.println( "====================== PUT REQUEST ========================");
		System.out.println("The Header Type Is: ");
		HashMap data = new HashMap();
		data.put("id", "102");
		data.put("name", "Ottoman Emporar 2");
		data.put("releaseDate", "2021-02-02T00:46:17.668Z");
		data.put("reviewScore", "8");
		data.put("category", "Articles");
		data.put("rating", "8");
		
		Response response =
		given().
			contentType("application/json").
			body(data).
		when().
			put("http://localhost:8080/app/videogames/102").
		then().
			statusCode(200).
			body("VideoGame.id", equalTo("102")).
			body("VideoGame.name", equalTo("Ottoman Emporar 2")).
		    log().all().
			extract().response();
		int curentStatuscode = response.statusCode();
		AssertJUnit.assertEquals(curentStatuscode, 200);
		System.err.println("The Response Code is ("+curentStatuscode+")");
	}
	// Returns all the newly added videos games in the DB
		@Test(priority = 5, groups = "Get Methods")
		public void test_get03() {
			System.out.println("\n============================================================");
			System.out.println("======================= GET REQUEST ========================\n");
			System.out.println("The Header Type Is: ");
			Response response =
			given().
				contentType("application/json").
				accept(ContentType.XML).
				
			when().
				get("http://localhost:8080/app/videogames/102").
			then().
				body("VideoGame.id", equalTo("102")).
				body("VideoGame.name", equalTo("Ottoman Emporar 2")).
				body("VideoGame.reviewScore", equalTo("8")).
				statusCode(200).
				log().all().
				extract().response();
			int curentStatuscode = response.statusCode();
			AssertJUnit.assertEquals(curentStatuscode, 200);
			System.err.println("The Response Code is ("+curentStatuscode+")");
		}
		// Returns all the newly added videos games in the DB
		@Test(priority = 6, groups = "DELETE Methods")
		public void test_DELETE() {
			System.out.println("\n============================================================");
			System.out.println("======================= DELETE REQUEST ========================\n");
			System.out.println("The Header Type Is: ");
			Response response =
			given().
				contentType("application/json").
				accept(ContentType.XML).
			when().
				delete("http://localhost:8080/app/videogames/102").
			then().
				statusCode(200).
				log().all().
				extract().response();
			int curentStatuscode = response.statusCode();
			AssertJUnit.assertEquals(curentStatuscode, 200);
			System.err.println("The Response Code is ("+curentStatuscode+")");
			
		}
	
}

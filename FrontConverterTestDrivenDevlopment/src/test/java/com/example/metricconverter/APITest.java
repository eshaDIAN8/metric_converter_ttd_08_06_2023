package com.example.metricconverter;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import org.junit.Test;

public class APITest {

	//methd nm with given then 
	//nm shd be api tstng
	@Test
	public void testGivenMetricUnitEmpty_thenCheckingServiceChainingResponse() {

		//?fromUnit=meter&toUnit=mm&value=9
		RestAssured.baseURI = "http://localhost:8089";
		String flow1_To_Flow2_TestResult = given().log().all().when().queryParam("fromUnit", "").queryParam("toUnit", "cm").queryParam("value", "9").get("/converter").then().assertThat().log().all()
				.statusCode(200).extract().response().asString();

		System.out.println("flow1 To Flow2 TestResult::" + flow1_To_Flow2_TestResult);

	}

	
	@Test
	public void testGivenMetricUnit_thenCheckingServiceChainingResponse() {

		//?fromUnit=meter&toUnit=mm&value=9
		RestAssured.baseURI = "http://localhost:8089";
		String flow1_To_Flow2_TestResult = given().log().all().when().queryParam("fromUnit", "9").queryParam("toUnit", "cm").queryParam("value", "9").get("/converter").then().assertThat().log().all()
				.statusCode(200).extract().response().asString();

		System.out.println("flow1 To Flow2 TestResult::" + flow1_To_Flow2_TestResult);

	}

}

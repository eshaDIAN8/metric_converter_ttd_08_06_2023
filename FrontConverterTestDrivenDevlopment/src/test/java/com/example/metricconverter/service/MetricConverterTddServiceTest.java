package com.example.metricconverter.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.service.MetricConverterTddService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MetricConverterTddServiceTest {
	
	
	@InjectMocks
	MetricConverterTddService service;


	@Mock
	private TestTemplate testRestTemplate;
	

	@Mock
	MetricConverterTddDao dao ;
	
	
	@Test
	public void testGivenMetricUnit_whenTryingToFetchFinalCalculatedResult_failreScenrio() {
		
		String fromUnit ="meter";
		String toUnit ="cm";
	
	

    	when(dao.getFormula(fromUnit,toUnit)).thenReturn("*100");
    	double convertedResult = service.getConvertedResult(fromUnit,toUnit, 5);
    	String convertedResult1 = String.valueOf(convertedResult);
    	Assert.assertEquals("500.0", convertedResult1);
    	
    
	}
	
	@Test(expected=NullPointerException.class)
	public void testGivenMetricUnit_whenTryingToTestException() {
		NullPointerException e = null;
		String fromUnit ="meter";
		String toUnit ="cm";
	
	

    	when(dao.getFormula(fromUnit,toUnit)).thenReturn("*100");
    	 service.getConvertedResult(fromUnit,toUnit, 5);
    	//String convertedResult1 = String.valueOf(convertedResult);
    	Assert.assertEquals(e.getMessage(),service.getConvertedResult(fromUnit,toUnit, 5) );
    	
    
	}
	
	/*@Test
	public void testService_Failure_Scenario_Unit_Testing() {
		
		String fromUnit ="meter";
		String toUnit ="cm";
	

		when(dao.getFormula(fromUnit,toUnit)).thenReturn("*100");
		double convertedResult = service.getConvertedResult(fromUnit,toUnit, 5);
    	String convertedResult1 = String.valueOf(convertedResult);
    	 Assert.assertEquals("500.0", convertedResult1);
    	
    
	}*/
	
	
	@Test
	public void testGivenMetricUnit_whenTryingToFetchFinalCalculatedResult_sucessScenrio() {
		
		String convertedUnit ="meter-cm";
		String formula ="*100";

    	/*when(dao.getFormula(convertedUnit)).thenReturn("*100");
    	double convertedResult = service.getConvertedResult(convertedUnit, 5);
    	String convertedResult1 = String.valueOf(convertedResult);
    	 Assert.assertEquals("500.0", convertedResult1);*/
    	
    
	}
	
	@Test
	public void testGivenMetricUnit_whenTryingToFetchFinalCalculatedResult_refactrScenrio() {
		
		String convertedUnit ="meter-cm";
		String formula ="*100";

    	/*when(dao.getFormula(convertedUnit)).thenReturn("*100");
    	double convertedResult = service.getConvertedResult(convertedUnit, 5);
    	String convertedResult1 = String.valueOf(convertedResult);
    	 Assert.assertEquals("500.0", convertedResult1);*/
    	
    
	}

}

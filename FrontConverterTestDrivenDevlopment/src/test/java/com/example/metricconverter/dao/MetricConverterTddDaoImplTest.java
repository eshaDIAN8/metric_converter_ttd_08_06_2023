package com.example.metricconverter.dao;

import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.dao.MetricConverterTddDaoImpl;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebMvcTest
@ExtendWith(MockitoExtension.class)
public class MetricConverterTddDaoImplTest {

	
	@InjectMocks
	MetricConverterTddDao dao = new MetricConverterTddDaoImpl();

	@Value(value = "${local.server.port}")
	private int port;
	@Mock
	private RestTemplate testRestTemp;

	@Test
	public void testIntegrationGivenMetricUnit_whenRetrievingGetFormulaFromCrud_failureScenrio() {

		String fromUnit = "meter";
		String toUnit = "cm";
		String formula = dao.getFormula(fromUnit, toUnit);

		System.out.println("getting formula from crud service::" + formula);
		Assert.assertEquals("*100", formula);

	}
	
	
	
	@Test(expected=HttpClientErrorException.class)
	public void testIntegrationGivenMetricUnit_thenCheckingException() {
		HttpClientErrorException e = null ;
		String fromUnit = "meter";
		String toUnit = "cm";
		String formula = dao.getFormula("meter", "mm");

		System.out.println("getting formula from crud service::" + formula);
		Assert.assertEquals(e.getMessage(), formula);

	    }

	/*
	 * @Test public void testIntegrationGivenMetricUnit_whenRetrievingGetFormulaFromCrud_SuccsScenrio() throws
	 * JSONException { String fromUnit ="meter"; String toUnit ="cm"; String formula
	 * = dao.getFormula(fromUnit,toUnit);
	 * 
	 * System.out.println("getting formula from crud service::"+formula);
	 * Assert.assertEquals("*100",formula );
	 * 
	 * }
	 */

	
	@Test
	public void testIntegrationGivenMetricUnitWhenRetrievingGetFormula_byMockingCrudServcCall() {
		String fromUnit = "meter";
		String toUnit = "cm";
		
		String uri = "http://localhost:8080/getConvertedUnit/crud?fromUnit="+fromUnit+"&toUnit="+toUnit;
		Mockito.when(testRestTemp.getForObject(
				uri, String.class)).thenReturn("*1000");
		String formula = dao.getFormula(fromUnit, toUnit);
		System.out.println("formula::" + formula);

		Assert.assertEquals("*1000", formula);

	}
	
}

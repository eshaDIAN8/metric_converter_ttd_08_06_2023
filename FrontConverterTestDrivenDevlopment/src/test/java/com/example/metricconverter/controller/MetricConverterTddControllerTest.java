package com.example.metricconverter.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.service.MetricConverterTddService;

@ExtendWith(SpringExtension.class)
// @WebMvcTest(TtdFrontController.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class MetricConverterTddControllerTest {

	@InjectMocks
	private MetricConverterTddController controller;

	@Mock
	private MetricConverterTddService service;

	private MockMvc mockMvc;

	@Mock
	private MockMvcRequestBuilders mockMvcBuilder;

	@Mock
	MetricConverterTddDao dao;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}
	// test methd nm shd be behav specifc with given nd when

	@Test
	public void testGivenMetricUnit_whenRetrievingFormulaFromCrudService() {

		String fromUnit = "meter";
		String toUnit = "cm";
		double value = 5;

		when(service.getConvertedResult(fromUnit, toUnit, value)).thenReturn(500.0);

		ResponseEntity<String> response = controller.getFormulaFromCrud(fromUnit, toUnit, "5");

		Assert.assertEquals("500.0", response.getBody());

	}

	@Test
	public void testGivenMetricUnit_whenRetrievingFormulaByFailreScenaro() throws Exception {

		String fromUnit = "meter";
		String toUnit = "cm";
		double value = 5;

		String uri = "/converter?fromUnit={fromUnit}&toUnit={toUnit}&value= {value}";

		when(service.getConvertedResult(fromUnit, toUnit, value)).thenReturn(500.0);
		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromUnit", "meter").param("toUnit", "cm").param("value", "5"))
				.andExpect(status().isOk()).andReturn();

		Assert.assertEquals("500.0", mvcResult1.getResponse().getContentAsString());
	}

	@Test
	public void testGivenMetricUnit_whenRetrievingFormulaBySuccssScenro() throws Exception {

		String fromUnit = "meter";
		String toUnit = "cm";
		double value = 5;

		String uri = "/converter?fromUnit={fromUnit}&toUnit={toUnit}&value= {value}";
		when(service.getConvertedResult(fromUnit, toUnit, value)).thenReturn(500.0);

		MvcResult mvcResult1 = (MvcResult) mockMvc
				.perform(get("/converter?").param("fromUnit", "meter").param("toUnit", "cm").param("value", "5"))
				.andExpect(status().isOk()).andReturn();

	}

}

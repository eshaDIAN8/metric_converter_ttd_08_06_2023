package com.example.metricconverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.controller.MetricConverterTddController;
import com.example.metricconverter.dao.MetricConverterTddDao;
import com.example.metricconverter.dao.MetricConverterTddDaoImplStatic;
import com.example.metricconverter.service.MetricConverterTddService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles({ "test" })
// @WebMvcTest
// @EnabledIf(value = "#{environment.getActiveProfiles()[0] == 'test'}",
// loadContext = true)
class MetricConverterTddApplicationTests {

	@Value(value = "${local.server.port}")
	private int port;

	@Autowired
	MetricConverterTddDao dao;

	@Autowired
	private TestRestTemplate testRestTemp;

	@Test
	void contextLoads() {

	}

	@Test
	public void testComponentTesting() throws Exception {

		String result = testRestTemp
				.getForObject("http://localhost:" + port + "/converter?fromUnit=meter&toUnit=cm&value=9", String.class);

		Assert.assertEquals("900.0", result);

	}

}

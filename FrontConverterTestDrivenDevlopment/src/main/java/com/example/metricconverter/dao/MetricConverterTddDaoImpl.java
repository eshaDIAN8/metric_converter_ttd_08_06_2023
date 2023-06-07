package com.example.metricconverter.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.metricconverter.service.MetricConverterTddService;

@Component
//@Profile({ "!test" })
public class MetricConverterTddDaoImpl implements MetricConverterTddDao {

	
	private static Logger logger = LogManager.getLogger(MetricConverterTddDaoImpl.class);
	
	
	
	@Autowired
	private RestTemplate restTmp;

	private String formula;

	public String getFormula(String fromUnit,String toUnit) {

		try {
		

			
			String uri = "http://localhost:8080/getConvertedUnit/crud?fromUnit="+fromUnit+"&toUnit="+toUnit;
            System.out.println("uri:"+uri);
		    formula = restTmp.getForObject(uri , String.class);
				
				logger.info("formula in front service:" + formula);
			
			//shd nt catch parent excptn
		} catch (HttpClientErrorException e) {
			logger.error("exception at the time of callng crud microservice " + e);
		}

		return formula;
	}

}



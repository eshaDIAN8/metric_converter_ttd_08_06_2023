package com.example.metricconverter.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.metricconverter.service.MetricConverterTddService;



// reqst mppng we cn bring it in get
//@RequestMapping("/converter")

@RestController
@Validated
public class MetricConverterTddController {
	// projct nm-dash in btwen nd in smallcase ,,tdd,metric-convertr
	
	
	

	private static Logger logger = LogManager.getLogger(MetricConverterTddController.class);

	@Autowired
	private MetricConverterTddService service;

	//
	

	// hv to dlr variables as request param
	//@GetMapping("/{fromUnit}/{toUnit}/{value}")
	//@RequestParam Optional<String>
	@GetMapping("/converter")
	public ResponseEntity<String> getFormulaFromCrud( @NotNull @NotEmpty @RequestParam   String fromUnit, 
			@NotNull @NotEmpty	@RequestParam   String toUnit,
			@NotNull @NotEmpty	@RequestParam  String value) {

		double unitToBeConvertedResult = 0;
		
		if(!fromUnit.equalsIgnoreCase(toUnit)) {
		
		// input validation by using bean validation
		
			// hv to move this logic in crud
			// hv to move if else
		//	String UnitConverterKey = fromUnit + "-" + toUnit;
		
			double dValue = Double.parseDouble(value);
			unitToBeConvertedResult = service.getConvertedResult(fromUnit,toUnit, dValue);
			logger.info("inside front controller " + unitToBeConvertedResult);

		}else {
			
			System.out.println("fromUnit and toUnit shd not be equal");
		}
			
		

		String finalConvertedResult = String.valueOf(unitToBeConvertedResult);
		logger.info(finalConvertedResult);

		return ResponseEntity.ok(finalConvertedResult);

	}

}

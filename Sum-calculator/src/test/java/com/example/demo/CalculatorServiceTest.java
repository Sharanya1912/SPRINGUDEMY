package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorServiceTest {
	
	@Autowired
	private CalculatorService calculatorService;
	

	@Test
	void testSum() {
		assertEquals(12,calculatorService.sum(5, 7));
	}

}

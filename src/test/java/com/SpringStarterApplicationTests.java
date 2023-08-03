package com;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStarterApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(SpringStarterApplicationTests.class);
	
	
	@Test
	void contextLoads() {
		
		logger.info("test cases calls !!!!!");
		
	}

}

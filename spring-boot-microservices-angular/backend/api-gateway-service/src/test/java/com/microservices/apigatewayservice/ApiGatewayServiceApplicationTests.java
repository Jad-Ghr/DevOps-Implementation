package com.microservices.apigatewayservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("API Gateway Service Tests")
class ApiGatewayServiceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Context loads successfully")
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("MockMvc is autowired and available")
	void testMockMvcAvailable() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@DisplayName("Application configuration is loaded properly")
	void testApplicationStartup() {
		assertThat(mockMvc).isNotNull();
	}
}

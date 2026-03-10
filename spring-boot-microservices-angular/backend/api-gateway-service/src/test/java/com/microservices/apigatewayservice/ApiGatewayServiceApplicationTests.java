package com.microservices.apigatewayservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayName("API Gateway Service Tests")
class ApiGatewayServiceApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	@DisplayName("Context loads successfully")
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("WebTestClient is autowired and available")
	void testWebTestClientAvailable() {
		assertThat(webTestClient).isNotNull();
	}

	@Test
	@DisplayName("Application configuration is loaded properly")
	void testApplicationStartup() {
		assertThat(applicationContext).isNotNull();
	}
}

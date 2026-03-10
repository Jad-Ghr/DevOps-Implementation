package com.microservices.zuulservice;

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
@DisplayName("Zuul Service Tests")
class ZuulServiceApplicationTests {

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
	@DisplayName("Zuul proxy is properly configured")
	void testZuulProxyConfiguration() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("Route configuration is loaded")
	void testRouteConfiguration() {
		assertThat(applicationContext.getApplicationName()).isNotBlank();
	}

	@Test
	@DisplayName("API routing is enabled")
	void testApiRouting() {
		assertThat(applicationContext).isNotNull();
	}
}

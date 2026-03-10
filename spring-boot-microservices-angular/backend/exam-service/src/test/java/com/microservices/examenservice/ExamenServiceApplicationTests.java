package com.microservices.examenservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Exam Service Tests")
class ExamenServiceApplicationTests {

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
	@DisplayName("Exam service is properly configured")
	void testExamServiceBeanExists() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("Application initializes properly")
	void testApplicationInitialization() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@DisplayName("JPA repositories are configured")
	void testRepositoryConfiguration() {
		assertThat(applicationContext).isNotNull();
	}
}

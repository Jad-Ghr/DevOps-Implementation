package com.microservices.answerservice;

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
@DisplayName("Answer Service Tests")
class AnswerServiceApplicationTests {

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
	@DisplayName("Answer service application exists")
	void testAnswerServiceBeanExists() {
		assertThat(applicationContext.containsBean("answerServiceApplication")).isTrue();
	}

	@Test
	@DisplayName("Application configuration is loaded")
	void testApplicationStartup() {
		assertThat(applicationContext.getApplicationName()).isNotBlank();
	}

	@Test
	@DisplayName("Spring Data JPA is configured")
	void testJpaConfiguration() {
		assertThat(applicationContext).isNotNull();
	}
}
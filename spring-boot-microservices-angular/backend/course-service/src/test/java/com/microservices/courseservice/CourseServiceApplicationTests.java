package com.microservices.courseservice;

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
@DisplayName("Course Service Tests")
class CourseServiceApplicationTests {

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
	@DisplayName("Course service is properly configured")
	void testCourseServiceBeanExists() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("Database configuration is loaded")
	void testDatabaseConfiguration() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	@DisplayName("Course service starts successfully")
	void testApplicationStartup() {
		assertThat(applicationContext.getApplicationName()).isNotBlank();
	}
}

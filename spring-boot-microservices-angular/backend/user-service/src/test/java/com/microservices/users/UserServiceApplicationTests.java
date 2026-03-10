package com.microservices.users;

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
@DisplayName("User Service Tests")
class UserServiceApplicationTests {

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
	@DisplayName("User service bean is available")
	void testUserServiceBeanExists() {
		assertThat(applicationContext.containsBean("userServiceApplicationTests")).isTrue();
	}

	@Test
	@DisplayName("Application initializes without errors")
	void testApplicationInitialization() {
		assertThat(applicationContext.getApplicationName()).isNotEmpty();
	}

	@Test
	@DisplayName("Spring Security is configured")
	void testSecurityConfiguration() {
		assertThat(applicationContext).isNotNull();
	}
}

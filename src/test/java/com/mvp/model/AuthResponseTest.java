package com.mvp.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthResponseTest {
	
	@Test
	void getterTest() {
		AuthResponse authResponse = new AuthResponse("test",true);
		authResponse.getUsername();
		authResponse.isValid();
	}
	
	@Test
	void setterTest() {
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUsername("test");
		authResponse.setValid(true);
	}
}

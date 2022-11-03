package com.mvp.exceptions;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvp.exception.InvalidUserException;


@SpringBootTest(classes= {InvalidUserExceptionTest.class})
public class InvalidUserExceptionTest {
	
	
	@Test
	@Order(1)
	public void test_InvalidUserException() {
		InvalidUserException userExp = new InvalidUserException("User Not Found");
	}
}

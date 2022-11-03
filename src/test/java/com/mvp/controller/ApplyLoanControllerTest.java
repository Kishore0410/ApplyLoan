package com.mvp.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mvp.exception.InvalidUserException;
import com.mvp.feign.LoginClient;
import com.mvp.model.AuthResponse;
import com.mvp.model.LoanDetail;
import com.mvp.service.LoanService;



@SpringBootTest(classes= {ApplyLoanControllerTest.class})
public class ApplyLoanControllerTest {

	@Mock
	LoanService loanService;

	@Mock
	LoginClient login;

	@Spy
	@InjectMocks
	ApplyLoanController loanController;

	@Test
	public void test_applyLoan() {
		AuthResponse auth = new AuthResponse("test",false);
		ResponseEntity<AuthResponse> response=
				new ResponseEntity<AuthResponse>(auth,HttpStatus.OK);
		when(login.verifyToken(Mockito.anyString())).thenReturn(response);
		LoanDetail loanDetail = new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null");
		assertThrows(InvalidUserException.class, () -> {
			loanController.applyLoan("test", loanDetail);
		});

	}
	@Test
	public void test_applyLoan1() throws InvalidUserException {
		AuthResponse auth = new AuthResponse("test",true);
		ResponseEntity<AuthResponse> response=
				new ResponseEntity<AuthResponse>(auth,HttpStatus.OK);
		when(login.verifyToken(Mockito.anyString())).thenReturn(response);
		LoanDetail loanDetail = new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null");
		when(loanService.checkEligibility(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		loanController.applyLoan("test", loanDetail);
		when(loanService.checkEligibility(Mockito.anyString(),Mockito.anyString())).thenReturn(false);
		loanController.applyLoan("test", loanDetail);
	}
	
	@Test
	public void test_viewStatus() {
		List<LoanDetail> list= new ArrayList<>();
		list.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(loanService.getLoanDetailsByName(Mockito.anyString())).thenReturn(list);
		loanController.viewStatus("test", "test");
	}
}

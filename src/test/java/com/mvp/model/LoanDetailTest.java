package com.mvp.model;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanDetailTest {

	@Test
	void getterTest() {
		LoanDetail loanDetail = new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null");
		loanDetail.getLoanId();
		loanDetail.getUserName();
		loanDetail.getLoanType();
		loanDetail.getLoanAmount();
		loanDetail.getAppliedDate();
		loanDetail.getRateOfInterest();
		loanDetail.getLoanDuration();
		loanDetail.getStatus();
	}
	
	@Test
	void setterTest() {
		LoanDetail loanDetail = new LoanDetail();
		loanDetail.setLoanId(1);
		loanDetail.setUserName("test");
		loanDetail.setLoanType("TestLoan");
		loanDetail.setLoanAmount(12345);
		loanDetail.setAppliedDate(LocalDate.of(2020, 1, 8));
		loanDetail.setRateOfInterest(2.3f);
		loanDetail.setLoanDuration("5 Years");
		loanDetail.setStatus("null");
		
	}
}

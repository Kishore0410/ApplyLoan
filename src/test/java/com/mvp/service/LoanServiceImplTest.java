package com.mvp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvp.exception.InvalidUserException;
import com.mvp.model.LoanDetail;
import com.mvp.repo.LoanDetailRepo;

@SpringBootTest(classes= {LoanServiceImplTest.class})
public class LoanServiceImplTest {

	@Mock
	LoanDetailRepo repo;

	@InjectMocks
	LoanServiceImpl loanService;

	@InjectMocks
	InvalidUserException userExp;

	@Test
	@Order(1)
	public void test_applyLoan()
	{
		LoanDetail loanDetail = new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null");
		loanService.applyLoan(loanDetail);
		verify(repo,times(1)).save(loanDetail);
	}

	@Test
	@Order(2)
	public void test_checkEligibility() {
		List<LoanDetail> loans = new ArrayList<>();
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","Rejected"));
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(2,"test1234","CarLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(repo.getDetailByUserName(Mockito.anyString())).thenReturn(1);
		when(repo.getLoanStatusByName(Mockito.anyString())).thenReturn(loans);
		loanService.checkEligibility("test", "PersonalLoan");
	}
	
	@Test
	public void test_checkEligibility1() {
		List<LoanDetail> loans = new ArrayList<>();
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","Rejected"));
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(2,"test1234","CarLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(repo.getDetailByUserName(Mockito.anyString())).thenReturn(0);
		when(repo.getLoanStatusByName(Mockito.anyString())).thenReturn(loans);
		loanService.checkEligibility("test", "PersonalLoan");
	}
	

	@Test
	public void test_checkEligibility2() {
		List<LoanDetail> loans = new ArrayList<>();
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","Rejected"));
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(2,"test1234","CarLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(repo.getDetailByUserName(Mockito.anyString())).thenReturn(0);
		when(repo.getLoanStatusByName(Mockito.anyString())).thenReturn(loans);
		loanService.checkEligibility("test", "PersonalLoan");
	}

	@Test
	@Order(3)
	public void test_approveLoan() {
		List<LoanDetail> loans = new ArrayList<>();
		loans.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","Approved"));
		loans.add(new LoanDetail(2,"test1234","CarLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(3,"test1234","PropertyLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(4,"test1234","JumboLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(5,"test1234","EducationLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(5,"test1234","EducationLoan",800001L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(3,"test1234","PropertyLoan",1000003L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		loans.add(new LoanDetail(5,"test1234","EducationLoan",800000L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(repo.loandetails()).thenReturn(loans);
		loanService.approveLoan();

	}

	@Test
	@Order(4)
	public void test_getLoanStatus() {
		List<LoanDetail> list= new ArrayList<>();
		list.add(new LoanDetail(1,"test123","PersonalLoan",12345L,LocalDate.of(2020, 1, 8),1.3f,"5 Years","null"));
		when(repo.getLoanStatusByName(Mockito.anyString())).thenReturn(list);
		loanService.getLoanDetailsByName("test");
	}
}

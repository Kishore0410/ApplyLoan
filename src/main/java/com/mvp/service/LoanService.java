package com.mvp.service;

import java.util.List;

import com.mvp.model.LoanDetail;

public interface LoanService {
	
	public boolean checkEligibility(String username, String typeOfLoan);

	public void applyLoan(LoanDetail loanDetail);
	
	public void approveLoan();
	
	public List<LoanDetail> getLoanDetailsByName(String name);

}

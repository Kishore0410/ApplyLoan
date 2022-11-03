package com.mvp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mvp.model.LoanDetail;
import com.mvp.repo.LoanDetailRepo;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private LoanDetailRepo repo;

	//saves the loan detail in database
	@Override
	public void applyLoan(LoanDetail loanDetail) {
		repo.save(loanDetail);
	}

	@Override
	public boolean checkEligibility(String username, String typeOfLoan) {
		int nullcount = 0;
		int rejectedcount = 0;
		int count = repo.getDetailByUserName(username);
		if(count==0) {
			return true;
		}
		else {
			List<LoanDetail> availableLoan = repo.getLoanStatusByName(username);
			for(LoanDetail loan : availableLoan) {
				 nullcount = 0;
				 rejectedcount = 0;
				if(loan.getLoanType().equalsIgnoreCase(typeOfLoan)) {
					if(loan.getStatus().equalsIgnoreCase("Rejected")) {
						rejectedcount++;
					}
					if(loan.getStatus().equalsIgnoreCase("null")) {
						nullcount++;
					}
				}
			}
			System.out.println(rejectedcount+ "    " + nullcount);
			if(nullcount-rejectedcount<=0) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	@Scheduled(initialDelay  = 1000L, fixedDelay = 60 * 2000L)
	public void approveLoan() {
		List<LoanDetail> loans = repo.loandetails();
		for(LoanDetail loan : loans) {
			if(loan.getLoanAmount()<200000 && loan.getLoanType().equalsIgnoreCase("PersonalLoan")) {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Approved");
				repo.save(updatedloan);
			}
			else if(loan.getLoanAmount()<500000 && loan.getLoanType().equalsIgnoreCase("CarLoan")) {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Approved");
				repo.save(updatedloan);
			}
			else if(loan.getLoanAmount()<1000000 && loan.getLoanType().equalsIgnoreCase("PropertyLoan")) {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Approved");
				repo.save(updatedloan);
			}
			else if(loan.getLoanAmount()<300000 && loan.getLoanType().equalsIgnoreCase("JumboLoan")) {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Approved");
				repo.save(updatedloan);
			}
			else if(loan.getLoanAmount()<800000 && loan.getLoanType().equalsIgnoreCase("EducationLoan")) {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Approved");
				repo.save(updatedloan);
			}
			else {
				LoanDetail updatedloan = loan;
				updatedloan.setStatus("Rejected");
				repo.save(updatedloan);
			}

		}
	}

	@Override
	public List<LoanDetail> getLoanDetailsByName(String name) {
		List<LoanDetail> updatedLoan = repo.getLoanStatusByName(name);
		return updatedLoan;
	}

}

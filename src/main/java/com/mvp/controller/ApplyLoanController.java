package com.mvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import com.mvp.exception.InvalidUserException;
import com.mvp.feign.LoginClient;
import com.mvp.model.AuthResponse;
import com.mvp.model.LoanDetail;
import com.mvp.service.LoanService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApplyLoanController {

	@Autowired
	private LoanService loanService;

	@Autowired
	private LoginClient login;

	/**
	 * apply loan to user
	 * @return "Loan Applied Successfully" if applies for first time
	 * @return "Loan is under evaluation" if applies for second time
	 * @param LoanDetail
	 * @param token
	 * @throws InvalidUserException
	 */

	@PostMapping("/applyloan")
	public ResponseEntity<String> applyLoan(@RequestHeader("Authorization") String token,
			@RequestBody LoanDetail loanDetail) throws InvalidUserException {

		AuthResponse auth = login.verifyToken(token).getBody();
		if (!auth.isValid())
			throw new InvalidUserException("User is invalid");
		else {
			if (loanService.checkEligibility(loanDetail.getUserName(), loanDetail.getLoanType())) {
				loanService.applyLoan(loanDetail);
			} else {
				return new ResponseEntity<String>("Loan is under evaluation", HttpStatus.OK);
			}

		}
		return new ResponseEntity<String>("Loan Applied Successfully", HttpStatus.OK);
	}

	@GetMapping("/viewloan/{username}")
	public ResponseEntity<List<LoanDetail>> viewStatus(@RequestHeader("Authorization") String token,
			@PathVariable String username){

		List<LoanDetail> updatedLoan = loanService.getLoanDetailsByName(username);
		return new ResponseEntity<List<LoanDetail>>(updatedLoan,HttpStatus.OK);

	}

}

package com.mvp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Loan_details")
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	private String userName;
	
	private String loanType;
	
	private long loanAmount;
	
//	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDate appliedDate;
	
	private float rateOfInterest;
	
	private String loanDuration;
	
	private String status;
}

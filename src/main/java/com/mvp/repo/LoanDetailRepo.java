package com.mvp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mvp.model.LoanDetail;

@Repository
public interface LoanDetailRepo extends JpaRepository<LoanDetail, Integer>{

	//Gets user details by username
	@Query(value = "SELECT COUNT(*) FROM Loan_details p WHERE p.user_name = ?1", nativeQuery=true)
	public int getDetailByUserName(String username);
	
	@Query(value = "SELECT loan_type FROM Loan_details p WHERE p.user_name = ?1", nativeQuery=true)
	public List<String> getloanType(String username);
	
	@Query(value = "SELECT * FROM Loan_details", nativeQuery=true)
	public List<LoanDetail> loandetails();
	
	@Query(value = "SELECT * FROM Loan_details where user_name = ?1", nativeQuery = true)
	public List<LoanDetail> getLoanStatusByName(String name);
}

package com.ramanasoft.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramanasoft.www.mode.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

	Customer findByMobile(String mobile);
	
	//Customer findByCustomerid(long customerid);

	List<Customer> findByCustomerid(long customerid);

	Customer findByEmail(String email);
}

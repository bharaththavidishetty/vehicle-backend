package com.ramanasoft.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramanasoft.www.mode.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>
{
	List<Payment> findByVnumber(String vnumber);
	
	List<Payment> findAllByVnumber(String vnumber);
	
	List<Payment> findByCustomerid(long customerid);
	
	List<Payment> findByPaymentid(String paymentid);
}

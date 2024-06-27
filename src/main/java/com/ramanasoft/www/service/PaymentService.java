package com.ramanasoft.www.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.www.mode.Payment;
import com.ramanasoft.www.repository.PaymentRepository;



@Service
public class PaymentService 
{
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public Payment addData(Payment profile)
	{
		profile.setStartdate(LocalDate.now());
		profile.setEnddate(profile.getStartdate().plusYears(1).minusDays(1));
		return paymentRepository.save(profile);
	}
	
	public List<Payment> fetchAll()
	{
		return paymentRepository.findAll();
	}
	
	public List<Payment> fetch(long nmbr)
	{
		return paymentRepository.findByCustomerid(nmbr);
	}
}


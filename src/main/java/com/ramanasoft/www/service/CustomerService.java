package com.ramanasoft.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.www.mode.Customer;
import com.ramanasoft.www.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;
	
	
	public Customer addData(Customer cust)
	{
		
		return customerRepository.save(cust);
	}


	public List<Customer> fetchAll() {
		
		return customerRepository.findAll();
	}
	
	
	public Customer getcid(long cid) {
		
		return customerRepository.findByCustomerid(cid).get(0);
	}


	public Customer get(String mob) {
		
		return customerRepository.findByMobile(mob);
	}
	
	
	public Customer getEmail(String email) {
		
		return customerRepository.findByEmail(email);
	}


	public Customer updateMobile(long cid, String mobile) {
		Customer c = customerRepository.findByCustomerid(cid).get(0);
		c.setMobile(mobile);
		customerRepository.save(c);
		return c;
	}
	
	
	public Customer updateEmail(long cid, String email) {
		Customer c = customerRepository.findByCustomerid(cid).get(0);
		c.setEmail(email);
		customerRepository.save(c);
		return c;
	}
}

package com.ramanasoft.www.mode;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Payment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String paymentid;
	private long customerid;
	private String vnumber;
	private String vprice;
	private String vname;
	private String idv;
	private String vyear;
	private String premiumAmount;
	private LocalDate startdate;
	private LocalDate enddate;
}

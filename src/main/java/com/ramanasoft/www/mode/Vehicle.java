package com.ramanasoft.www.mode;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Vehicle 
{
	@Id
	private  Long id;
	private String vnumber;
	private String vname;
	private int vyear;
	
	
	
	
}

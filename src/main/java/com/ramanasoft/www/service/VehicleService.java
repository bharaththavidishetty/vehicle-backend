package com.ramanasoft.www.service;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.www.mode.Vehicle;
import com.ramanasoft.www.repository.VehicleRepository;

@Service
public class VehicleService 
{
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	public Vehicle addData(Vehicle veh)
	{
		return vehicleRepository.save(veh);
	}
	
	public List<Vehicle> fetchAll()
	{
		return vehicleRepository.findAll();
	}
	
	public String updateData(Vehicle veh) 
	{
		Vehicle v = vehicleRepository.findById(veh.getId()).get();
		v.setVname(veh.getVname());
		v.setVnumber(veh.getVnumber());
		v.setVyear(veh.getVyear());

		
		vehicleRepository.save(v);
		
		return "data saved";
	}
	
	public String findVehicle(String num)
	{
		Vehicle v = vehicleRepository.findByVnumber(num);
		
		if(v!=null)
		{
			return "The vehicle is registered";
		}
		else
		{
			return "the vehicle is not registered";
		}
		
		
	}
	
	public String findVeh(String name)
	{
		Vehicle v=vehicleRepository.findByVnumber(name);
		if(v!=null)
		{
			return "The vehicle is registered";
		}
		else
		{
			return "The vehicle Does not Exist";
		}
	}
	
	public Vehicle getVehicle(String num)
	{
		return vehicleRepository.findByVnumber(num);
	}

	public List<Double> caliculate(String price,int vyear)
	{
		List<Double> list = new ArrayList<Double>();
		int p = Integer.parseInt(price);
		int year = vyear;
		double claim_amount=0,premium_amount=0;
		
		if(year<=2024 && year>2021)
		{
			claim_amount=(p*60)/100.0;
			premium_amount=900;
		}
		else if(year>2019)
		{
			claim_amount=(p*55)/100.0;
			premium_amount=850;
		}
		else if(year>2015)
		{
			claim_amount=(p*50)/100.0;
			premium_amount=800;
		}
		else if(year>2013)
		{
			claim_amount=(p*45)/100.0;
			premium_amount=750;
		}
		else if(year>2010)
		{
			claim_amount=(p*40)/100.0;
			premium_amount=700;
		}
		else
		{
			claim_amount=(p*35)/100.0;
			premium_amount=650;
		}
		
		list.add(Math.ceil(claim_amount));
		list.add(premium_amount);
		
		return list;
	}
	
	  
	  


}

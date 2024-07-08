package com.ramanasoft.www.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ramanasoft.www.mode.Vehicle;
import com.ramanasoft.www.service.Otps;
import com.ramanasoft.www.service.VehicleService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/vehicle")
public class VehicleController 
{
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	Otps otpService;
	
	@PostMapping("/add")
	public Vehicle addData(@RequestBody Vehicle veh)
	{
		return vehicleService.addData(veh);
	}
	
	@GetMapping("/fetch")
	public List<Vehicle> fetchAll()
	{
		return vehicleService.fetchAll();
	}
	
	@PutMapping("/update")
	public String updateData(@RequestBody Vehicle veh)
	{
		return vehicleService.updateData(veh);
	}
	
	@GetMapping("/find/{num}")
	public String findVehicle(@PathVariable String num)
	{
		return vehicleService.findVehicle(num);
	}
	
	@GetMapping("/search")
	public String findVeh(@RequestParam String name)
	{
		return vehicleService.findVeh(name);
	}
	
	@GetMapping("/get/{num}")
	public Vehicle getVehicle(@PathVariable String num)
	{
		return vehicleService.getVehicle(num);
	}
	
	@GetMapping("/calculate")
	public List<Double> caliculate(@RequestParam String price,@RequestParam int vyear)
	{
		return vehicleService.caliculate(price,vyear);
	}
	
	
	@GetMapping("/sendOtp")
	public String mobileOtp(@RequestParam String mobile) 
	{
		return otpService.mobileOtp(mobile);
	}
	
	
	@GetMapping("/sendMobileOtp")
	public String sendOtp(@RequestParam String mobileno, @RequestParam String otp) {
	String url = "https://login4.spearuc.com/MOBILE_APPS_API/sms_api.php?type=smsquicksend&user=qtnextotp&pass=987654&sender=QTTINF"
	+ "&t_id=1707170494921610008&to_mobileno=" + mobileno
	+ "&sms_text=" + "Dear customer, use this OTP " + otp + " to signup into your Quality Thought Next account. This OTP will be valid for the next 15 mins";

	RestTemplate restTemplate = new RestTemplate();
	return restTemplate.getForObject(url, String.class);
	}
	
	@PostMapping("/sendEmailOtpForRegistration/{toEmail}")
	public String emailOtp(@PathVariable String toEmail) 
	{
		return otpService.emailOtp(toEmail);
	}
	
	
	@PostMapping("/sendEmailOtpForUpdation/{toEmail}")
	public String emailOtpForUpdation(@PathVariable String toEmail) 
	{
		return otpService.emailOtpForUpdation(toEmail);
	}
	

	@PostMapping("/sendEmail")
	public String quotEmail(@RequestParam String toEmail,@RequestParam String vnumber,@RequestParam String price,@RequestParam String idv,@RequestParam String premiumAmount)
	{
		return otpService.quotEmail(toEmail,vnumber,price,idv,premiumAmount);
	}

}

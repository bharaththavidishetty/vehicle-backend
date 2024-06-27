package com.ramanasoft.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
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

import com.ramanasoft.www.mode.Vehicle;
import com.ramanasoft.www.service.VehicleService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/vehicle")
public class VehicleController 
{
	
	@Autowired
	VehicleService vehicleService;
	
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
	public String sendOtp(@RequestParam String mobile) 
	{
		return vehicleService.sendOtp(mobile);
	}
	

	
	@PostMapping("/sendEmailOTPforUpdation/{toEmail}")
	public String emailorUpdation(@PathVariable String toEmail) {
	    String postUrl = "https://api.zeptomail.in/v1.1/email";
	    StringBuffer sb = new StringBuffer();
	    String otp = generateOTPforupdation();

	    try {
	        URL url = new URL(postUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Authorization", "Zoho-enczapikey PHtE6r0EFLjr3jMsp0QAt/+wE8TyN40tr+hmKFMVsIgUXqMFTk0Bqdl6wDPiqU8jXPJHR/ObzN5ttLOe5+ONdGrtZG1NXmqyqK3sx/VYSPOZsbq6x00etFUdcE3aUIbvetFq0ifQvdbcNA==");

	        JSONObject requestBody = new JSONObject();
	        JSONObject from = new JSONObject();
	        String email = "support@qtnext.com";
	        from.put("address", email);
	        requestBody.put("from", from);

	        JSONObject to = new JSONObject();
	        JSONObject emailAddress = new JSONObject();
	        emailAddress.put("address", toEmail);
	        to.put("email_address", emailAddress);
	        requestBody.put("to", new JSONObject[]{to});

	        requestBody.put("subject", "OTP to verify email ID for RS Insurance");

	        String greeting = "Thanks & Regards";
	        String ofcName = "RS Insurance pvt ltd.";
	        String address1 = "Madhapur, Hyderabad,";
	        String address2 = "Telangana, India. 500081";

	        StringBuilder emailContent = new StringBuilder();
	        emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
	                .append("We have received a request to update email address at RS Insurance pvt ltd. In order to finalize this change and we kindly ask you to verify this update by utilizing the OTP provided below:")
	                .append("<h3>").append("OTP: ").append(otp).append("</h3>")
	                .append("Please use this OTP to confirm the updation of your email address ").append("<br/>").append("<br/>")
	                .append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 9951489432.").append("<br/>").append("<br/>")
	                .append("If you did not request this OTP, please ignore this email.").append("<br/>").append("<br/>")
	                .append("Thank you for choosing RS Insurance pvt ltd").append("<h5>").append("<br/>")
	                .append(greeting).append("<br/>")
	                .append(ofcName).append("<br/>")
	                .append(address1).append("<br/>")
	                .append(address2).append("<br/>");

	        String emailContentString = emailContent.toString();
	        requestBody.put("htmlbody", emailContentString);

	        OutputStream os = conn.getOutputStream();
	        os.write(requestBody.toString().getBytes());
	        os.flush();

	        BufferedReader br;
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }

	        String output;
	        while ((output = br.readLine()) != null) 
	        {
	            sb.append(output);
	        }

	        br.close();
	        conn.disconnect();

	        return otp;
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        return otp;
	    }
	}

	public static String generateOTPforupdation() {
		long mill = System.currentTimeMillis();
        String str=Long.toString(mill);
        String otp = str.substring(str.length()-5);
        return otp;
		
	    //return "1234";
	}

	@PostMapping("/sendEmail")
	public String quotEmail(@RequestParam String toEmail,@RequestParam String vnumber,@RequestParam String price,@RequestParam String idv,@RequestParam String premiumAmount)
	{
		return vehicleService.quotEmail(toEmail,vnumber,price,idv,premiumAmount);
	}

}

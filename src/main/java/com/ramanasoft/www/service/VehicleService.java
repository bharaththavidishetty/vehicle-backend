package com.ramanasoft.www.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	  public String sendOtp(String mobile) {
			
			
	        long mill = System.currentTimeMillis();
	        String str=Long.toString(mill);
	        String otp = str.substring(str.length()-5);
			
			
	        String url = "https://login4.spearuc.com/MOBILE_APPS_API/sms_api.php?type=smsquicksend&user=qtnextotp&pass=987654&sender=QTTINF"
	                + "&t_id=1707170494921610008&to_mobileno=" + mobile
	                + "&sms_text=" + "Dear customer, use this OTP " + otp + " to signup into your Quality Thought Next account. This OTP will be valid for the next 15 mins";

	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.getForObject(url, String.class);
	        
	        
	        return otp;
	    }
	  
		public String quotEmail(String toEmail,String vnumber,String price,String idv,String premiumAmount)
		{
			String postUrl = "https://api.zeptomail.in/v1.1/email";
		    StringBuffer sb = new StringBuffer();
		    

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

		        requestBody.put("subject", "Pwd:Find Your Next Insvestment with RS Insurance pvt ltd");

		        String greeting = "Thanks & Regards";
		        String ofcName = "RS Insurance pvt ltd.";
		        String contact = "1800-258-2465";
		        String mail = "support@ramanasoft.com";
		        String address1 = "Madhapur, Hyderabad,";
		        String address2 = "Telangana, India. 500081";

		        StringBuilder emailContent = new StringBuilder();
		        emailContent.append("Dear Customer,").append("<br/>").append("<br/>")
		                .append("Thank you for choosing RS Insurance.").append("<br/>").append("<br/>")
		                .append("Here is your Quotation Details").append("<br/>").append("<br/>")
		                .append("We appreciate your trust in our services and looking forword to serve your insurance needs.").append("<br/>")
		                .append("<h3>").append("Quotation Details").append("</h3>")
		                .append("<table border='1px' border-collapse='collapse' >")
		                .append("<tr>")
		                .append("<th>").append("Vehicle Number").append("</th>")
		                .append("<th>").append("Vehicle Price").append("</th>")
		                .append("<th>").append("IDV Amount").append("</th>")
		                .append("<th>").append("Premium Amount").append("</th>")
		                .append("</tr>")
		                .append("<tr>")
		                .append("<td>").append(vnumber).append("</td>")
		                .append("<td>").append(price).append("</td>")
		                .append("<td>").append(idv).append("</td>")
		                .append("<td>").append(premiumAmount).append("</td>")
		                .append("</tr>")
		                .append("</table>").append("<br/>").append("<br/>")
		                .append("Thank you for choosing RS Insurance pvt ltd").append("<h5>").append("<br/>")
		                .append(greeting).append("<br/>")
		                .append(ofcName).append("<br/>")
		                .append(contact).append("<br/>")
		                .append(mail).append("<br/>")
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

		        
		    }
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		        
		    }
			return "this";
		}

}

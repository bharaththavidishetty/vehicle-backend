package com.ramanasoft.www.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Otps 
{
	
	//method for mobile otp 
	public String mobileOtp(String mobile) {
        long mill = System.currentTimeMillis();
        String str=Long.toString(mill);
        String otp = str.substring(str.length()-5);
		
		String msg = "hello namaste bayya";
        String url = "https://login4.spearuc.com/MOBILE_APPS_API/sms_api.php?type=smsquicksend&user=qtnextotp&pass=987654&sender=QTTINF"
        		+ "&t_id=1707170494921610008&to_mobileno=" + mobile
        		+ "&sms_text=" + "Dear customer, use this OTP " + otp +" to signup into your Quality Thought Next account. This OTP will be valid for the next 15 mins";
        
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
        
        
        return otp;
    }
	
	
	// email otp for registration
	public String emailOtp(String toEmail) {
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
	        from.put("name", "RSInsurance");
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
	                .append("Thank you for signing up with RS Insurance pvt ltd. To complete your registration, please use the OTP provided below to verify your email address.")
	                .append("<h3>").append("OTP: ").append(otp).append("</h3>")
	                .append("Please keep this OTP confidential and do not share it with anyone.").append("<br/>").append("<br/>")
	                .append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.").append("<br/>").append("<br/>")
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


	//email otp for updation
	public String emailOtpForUpdation(String toEmail) {
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
	        from.put("name", "RSInsurance");
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
	                .append("We have received a request to update email address at RS Insurance pvt ltd. In order to finalize this change and we kindly ask you to verify this update by utilizing the OTP provided below.")
	                .append("<h3>").append("OTP: ").append(otp).append("</h3>")
	                .append("Please use this OTP to confirm the updation of your email address").append("<br/>").append("<br/>")
	                .append("If you did not initiate this change, or if you have any concerns, please contact our support team immediately at support@rsinsurance.com or +91 1800-143-123.").append("<br/>").append("<br/>")
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
	        from.put("name", "RSInsurance");
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

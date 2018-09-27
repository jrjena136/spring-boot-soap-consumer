package com.learning.jyoti.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jyoti.app.vo.CustomerVO;
import com.learning.jyoti.app.wsdl.CustomerProfileResponse;

@RequestMapping("/spring-boot-soap-consumer")
@RestController
@SpringBootApplication
public class SpringBootSoapConsumerApplication{
	private static final Logger log = LoggerFactory.getLogger(SpringBootSoapConsumerApplication.class);
	@Autowired
	CustomerInfoClient client;

	/*String mobile = "8059050512";
	String circle = "HR";
	String channel = "WSS Portal";
	String lob = "Postpaid";*/

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapConsumerApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		CustomerProfileResponse response = client.getCustomerResponse(mobile, channel, lob, circle);
		CustomerProfileResponse2 res = response.getCustomerProfileResponse();
		BusinessOutput output = res.getBusinessOutput();
		log.info("final response : " + output.toString());
	}*/
	
	@PostMapping("/getCustomer")
	public CustomerProfileResponse getCustomerInfo(@RequestBody CustomerVO customer) {
		CustomerProfileResponse response = client.getCustomerResponse(customer.getMsisdn(), customer.getChannel(),
				customer.getLog(), customer.getCircle());
		log.info("response : " + response.getCustomerProfileResponse().getBusinessOutput());
		return response;
	}
}

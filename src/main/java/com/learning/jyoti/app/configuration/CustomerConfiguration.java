package com.learning.jyoti.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.learning.jyoti.app.CustomerInfoClient;

@Configuration
public class CustomerConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.learning.jyoti.app.wsdl");
		return marshaller;
	}

	@Bean
	public CustomerInfoClient customerClient(Jaxb2Marshaller marshaller) {
		CustomerInfoClient greetingClient = new CustomerInfoClient();
		greetingClient.setDefaultUri("http://172.30.6.47:9083/PostpaidCustomerProfileService/services/");
		greetingClient.setMarshaller(marshaller);
		greetingClient.setUnmarshaller(marshaller);
		return greetingClient;
}

}

package com.learning.jyoti.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.learning.jyoti.app.wsdl.CustomerProfileRequest;
import com.learning.jyoti.app.wsdl.CustomerProfileRequest2;
import com.learning.jyoti.app.wsdl.CustomerProfileResponse;
import com.learning.jyoti.app.wsdl.HeaderInfo;
import com.learning.jyoti.app.wsdl.InputMessageHeader;

public class CustomerInfoClient extends WebServiceGatewaySupport {
	private static final Logger log = LoggerFactory.getLogger(CustomerInfoClient.class);

	public CustomerProfileResponse getCustomerResponse(String mobile, String channel, String lob, String circle) {
		CustomerProfileRequest request = new CustomerProfileRequest();
		CustomerProfileRequest2 req = new CustomerProfileRequest2();
		InputMessageHeader inputHeader = new InputMessageHeader();
		HeaderInfo headerInfo = new HeaderInfo();
		headerInfo.setChannelType("WSS Portal");
		headerInfo.setCircle(circle);
		headerInfo.setLob(lob);
		headerInfo.setMobileNo(mobile);
		inputHeader.setHeaderInfo(headerInfo);
		req.setInputMessageHeader(inputHeader);
		request.setCustomerProfileRequest(req);

		CustomerProfileResponse response = (CustomerProfileResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://172.30.6.47:9083/PostpaidCustomerProfileService/services/CustomerProfileServiceSOAP", request,
				new SoapActionCallback(
						"http://172.30.6.47:9083/PostpaidCustomerProfileService/services/CustomerProfileServiceSOAP"));
		log.info("Response : " + response);

		return response;

	}

}

package com.arguijo.ws.soap.config;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arguijo.ws.soap.FileWsImpl;

@Configuration
public class WebServiceConfig {
	@Autowired
	private Bus bus;

	@Bean
	public Endpoint endpoint() {
		System.out.println("Configuration started");
		Endpoint endpoint = new EndpointImpl(bus, new FileWsImpl());
		endpoint.publish("/fileWs");
		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		binding.setMTOMEnabled(true);
		return endpoint;
	}

}

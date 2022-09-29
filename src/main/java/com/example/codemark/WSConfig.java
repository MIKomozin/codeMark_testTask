package com.example.codemark;

import javax.xml.ws.Endpoint;

import com.example.codemark.service.UserServiceImpl;
import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSConfig {

    private final Bus bus;
    private final UserServiceImpl userService;

    @Autowired
    public WSConfig(Bus bus, UserServiceImpl userService) {
        this.bus = bus;
        this.userService = userService;
    }

    @Bean
    public Endpoint userServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/wsdlUserService");
        return endpoint;
    }
}
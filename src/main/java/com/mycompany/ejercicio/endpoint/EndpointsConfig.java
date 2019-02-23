package com.mycompany.ejercicio.endpoint;

import javax.xml.ws.Endpoint;

import com.mycompany.ejercicio.cxf.RoleServiceSoapPortType;
import com.mycompany.ejercicio.cxf.UserService;
import com.mycompany.ejercicio.cxf.UserServiceSoapPortType;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ws.config.annotation.EnableWs;

@Configuration
public class EndpointsConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private Environment env;

    @Bean
    public UserServiceSoapPortType userService() {
        return new UserServiceEndpoint();
    }

    @Bean
    public RoleServiceSoapPortType roleService() {
        return new RoleServiceEndpoint();
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService());
        endpoint.publish("/user");
        //endpoint.setWsdlLocation("Customer1.0.wsdl");
        return endpoint;
    }

    @Bean
    public Endpoint roleEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, roleService());
        endpoint.publish("/role");
        //endpoint.setWsdlLocation("Customer1.0.wsdl");
        return endpoint;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

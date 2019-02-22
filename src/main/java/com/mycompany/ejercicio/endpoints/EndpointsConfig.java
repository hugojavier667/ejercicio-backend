package com.mycompany.ejercicio.endpoints;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointsConfig {

    @Autowired
    private Bus bus;

    @Bean(name = "user")
    public Endpoint userEndpoint() {
        EndpointImpl endpoint =
                new EndpointImpl(bus, new UserImpl());
        endpoint.publish("/user");

        return endpoint;
    }

    @Bean(name = "role")
    public Endpoint roleEndpoint() {
        EndpointImpl endpoint =
                new EndpointImpl(bus, new RoleImpl());
        endpoint.publish("/role");

        return endpoint;
    }
}

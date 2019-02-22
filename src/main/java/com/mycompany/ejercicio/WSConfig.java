package com.mycompany.ejercicio;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/*@EnableWs
@Configuration*/
public class WSConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "userDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ejercicioSchema)
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserDetailsPort");
        wsdl11Definition.setLocationUri("/service/user-details");
        wsdl11Definition.setTargetNamespace("http://mycompany.com/xml/ejercicio");
        wsdl11Definition.setSchema(ejercicioSchema);
        return wsdl11Definition;
    }

    @Bean(name = "userListWsdl")
    public DefaultWsdl11Definition userListWsdl11Definition(XsdSchema ejercicioSchema)
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserListPort");
        wsdl11Definition.setLocationUri("/service/user-list");
        wsdl11Definition.setTargetNamespace("http://mycompany.com/xml/ejercicio");
        wsdl11Definition.setSchema(ejercicioSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema ejercicioSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("ejercicio.xsd"));
    }
}

package com.mycompany.ejercicio.cxf;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2019-02-21T22:35:25.645-05:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "UserService", 
                  wsdlLocation = "classpath:wsdl/WSDLEjercicio.wsdl",
                  targetNamespace = "http://mycompany.com/ejercicio/cxf") 
public class UserService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://mycompany.com/ejercicio/cxf", "UserService");
    public final static QName UserPort = new QName("http://mycompany.com/ejercicio/cxf", "UserPort");
    static {
        URL url = UserService.class.getClassLoader().getResource("wsdl/WSDLEjercicio.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(UserService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/WSDLEjercicio.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public UserService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public UserService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns UserPortType
     */
    @WebEndpoint(name = "UserPort")
    public UserPortType getUserPort() {
        return super.getPort(UserPort, UserPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserPortType
     */
    @WebEndpoint(name = "UserPort")
    public UserPortType getUserPort(WebServiceFeature... features) {
        return super.getPort(UserPort, UserPortType.class, features);
    }

}

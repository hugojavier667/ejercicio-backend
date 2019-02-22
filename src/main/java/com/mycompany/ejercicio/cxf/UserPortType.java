package com.mycompany.ejercicio.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2019-02-21T22:35:25.556-05:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://mycompany.com/ejercicio/cxf", name = "UserPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface UserPortType {

    @WebMethod(operationName = "UserDetails")
    @WebResult(name = "user", targetNamespace = "", partName = "user")
    public User userDetails(
        @WebParam(partName = "id", name = "id", targetNamespace = "")
        int id
    );

    @WebMethod(operationName = "ListAllUsers")
    @WebResult(name = "NewPart", targetNamespace = "", partName = "NewPart")
    public User listAllUsers();
}
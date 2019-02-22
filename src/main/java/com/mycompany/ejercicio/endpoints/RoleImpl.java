package com.mycompany.ejercicio.endpoints;

import com.mycompany.ejercicio.cxf.Role;
import com.mycompany.ejercicio.cxf.RoleServiceSoapPortType;

public class RoleImpl implements RoleServiceSoapPortType {
    @Override
    public Role roleDetails(int id) {
        Role role = new Role();
        role.setId(id);
        role.setName("admin");
        role.setDescription("Role");

        return role;
    }
}

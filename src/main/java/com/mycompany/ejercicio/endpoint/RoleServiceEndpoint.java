package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.RoleServiceSoapPortType;
import com.mycompany.ejercicio.cxf.role.*;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceEndpoint implements RoleServiceSoapPortType {

    @Autowired
    private RoleServiceController roleServiceController;

    @Override
    public GetRoleByNameResponse roleDetails(GetRoleByName parameters) throws RoleExcepction {
        return roleServiceController.getRoleByName(parameters.getRoleRequest());
    }

    @Override
    public AddRoleResponse createRole(AddRole parameter) throws RoleExcepction {
        return this.roleServiceController.addRole(parameter.getAddRoleRequest());
    }

    @Override
    public DeleteRoleReturn deleteRole(DeleteRoleRequest deleteRoleRequest) throws RoleExcepction {
        return this.roleServiceController.deleteRoleByName(deleteRoleRequest);
    }

    @Override
    public RolesReturn getRoles() {
        return this.roleServiceController.getRoles();
    }

    @Override
    public AddRoleReturn updateRole(UpdateRoleRequest updateRoleRequest) throws RoleExcepction {
        return this.roleServiceController.updateRole(updateRoleRequest);
    }
}

package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.ArrayOfRole;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.role.*;
import com.mycompany.ejercicio.entities.role.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceController {

    @Autowired
    private RoleDAO roleDAO;

    public GetRoleByNameResponse getRoleByName(RoleRequest roleRequest) throws RoleExcepction, DataAccessException {

        RoleReturn rr = new RoleReturn();
        String name = roleRequest.getName();

        Role role = roleDAO.findByName(name);

        if (role != null) {
            rr.setSuccess(true);
            rr.setRoleResult(role);
        } else {
            rr.setSuccess(false);
            rr.setErrorCode(101);//role not found
        }

        GetRoleByNameResponse getRoleByIdResponse = new GetRoleByNameResponse();
        getRoleByIdResponse.setGetRoleByNameResult(rr);

        return getRoleByIdResponse;
    }

    public RolesReturn getRoles() throws DataAccessException {

        List<Role> roleList = roleDAO.findAll();
        RolesReturn rr = new RolesReturn();
        ArrayOfRole ar = new ArrayOfRole();
        ar.getRole().addAll(roleList);

        rr.setSuccess(true);
        rr.setRolesResult(ar);

        return rr;
    }

    AddRoleResponse addRole(AddRoleRequest addRoleRequest) {

        Role role = addRoleRequest.getRole();
        AddRoleReturn arr = new AddRoleReturn();
        try {
            arr.setSuccess(true);
            this.roleDAO.save(role);
        } catch (Exception e) {
            //save was not possible due to validations (unique name constraint, for example)
            arr.setSuccess(false);
            arr.setErrorCode(102);
            arr.setErrorMessage(e.getMessage());//this error message must be adapted to a friendly message
        }

        AddRoleResponse addRoleResponse = new AddRoleResponse();
        addRoleResponse.setAddRoleResult(arr);
        return addRoleResponse;
    }

    DeleteRoleReturn deleteRoleByName(DeleteRoleRequest deleteRoleRequest) throws DataAccessException {

        String name = deleteRoleRequest.getName();
        int affectedRows = this.roleDAO.deleteByName(name);
        DeleteRoleReturn dcr = new DeleteRoleReturn();

        if (affectedRows > 0) {
            dcr.setSuccess(true);
        } else {
            dcr.setSuccess(false);
            dcr.setErrorCode(103);
        }

        return dcr;
    }

    public AddRoleReturn updateRole(UpdateRoleRequest updateRoleRequest) {
        Role role = updateRoleRequest.getRole();
        AddRoleReturn addRoleReturn = new AddRoleReturn();
        try {
            addRoleReturn.setSuccess(true);
            this.roleDAO.update(role);
        } catch (Exception e) {
            //save was not possible due to validations (unique login constraint, for example)
            addRoleReturn.setSuccess(false);
            addRoleReturn.setErrorCode(102);
            addRoleReturn.setErrorMessage(e.getMessage());//this error message must be adapted to a friendly message
        }

        return addRoleReturn;
    }
}

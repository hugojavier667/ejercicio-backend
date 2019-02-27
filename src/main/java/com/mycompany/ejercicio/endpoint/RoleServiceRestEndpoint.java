package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.entities.role.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleServiceRestEndpoint {

    @Autowired
    RoleDAO roleDAO;

    @GetMapping
    public List<Role> getAllRoles() throws DataAccessException {
        return roleDAO.findAll();
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) throws RoleExcepction, DataAccessException {
        return roleDAO.save(role);
    }

    @GetMapping(value = "/{name}")
    public Role getRoleByName(@PathVariable String name) throws RoleExcepction, DataAccessException {
        return roleDAO.findByName(name);
    }

    @PatchMapping
    public Role updateRole(@RequestBody Role role) throws RoleExcepction, DataAccessException {
        return roleDAO.update(role);
    }

    @DeleteMapping(value = "/{name}")
    public int deleteRoleByName(@PathVariable String name) throws DataAccessException {
        return roleDAO.deleteByName(name);
    }
}

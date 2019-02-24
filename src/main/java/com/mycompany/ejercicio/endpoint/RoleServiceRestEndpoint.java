package com.mycompany.ejercicio.endpoint;

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
    public List<Role> getAllRoles(){
        return roleDAO.findAll();
    }

    @PostMapping
    public Role saveRole(@RequestBody Role role) throws RoleExcepction {
        return roleDAO.save(role);
    }

    @GetMapping(value = "/{name}")
    public Role getRoleByName(@PathVariable String name){
        return roleDAO.findByName(name);
    }

    @PatchMapping
    public Role updateRole(@RequestBody Role role) throws RoleExcepction {
        return roleDAO.update(role);
    }

    @DeleteMapping(value = "/{name}")
    public int deleteRoleByName(@PathVariable String name){
        return roleDAO.deleteByName(name);
    }
}

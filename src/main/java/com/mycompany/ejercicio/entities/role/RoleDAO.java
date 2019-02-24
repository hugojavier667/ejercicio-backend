package com.mycompany.ejercicio.entities.role;

import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;

import java.util.List;

public interface RoleDAO {

    Role findByName(String name) throws RoleExcepction;

    List<Role> findAll();

    Role save(Role role) throws RoleExcepction;

    int deleteByName(String name);

    Role update(Role role) throws RoleExcepction;
}

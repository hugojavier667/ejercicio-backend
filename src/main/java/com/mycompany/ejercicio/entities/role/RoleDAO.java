package com.mycompany.ejercicio.entities.role;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;

import java.util.List;

public interface RoleDAO {

    Role findByName(String name) throws RoleExcepction, DataAccessException;

    List<Role> findAll() throws DataAccessException;

    List<Role> findAllByUserLogin(String login) throws DataAccessException;

    Role save(Role role) throws RoleExcepction, DataAccessException;

    int deleteByName(String name) throws DataAccessException;

    Role update(Role role) throws RoleExcepction, DataAccessException;
}

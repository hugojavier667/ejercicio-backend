package com.mycompany.ejercicio.entities.role;

import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("ldap")
public class RoleDAOImplLdap implements RoleDAO {
    @Override
    public Role findByName(String name) {
        Role role = new Role();
        role.setName(name + " ldap");
        return role;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role save(Role role) throws RoleExcepction {
        return null;
    }

    @Override
    public int deleteByName(String name) {
        return 0;
    }

    @Override
    public Role update(Role role) throws RoleExcepction {
        return null;
    }
}

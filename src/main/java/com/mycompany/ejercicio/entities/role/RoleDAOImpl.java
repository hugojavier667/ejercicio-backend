package com.mycompany.ejercicio.entities.role;

import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.exception.RoleException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("db")
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Role findByName(String name) {
        RoleEntity re = this.roleRepository.findByName(name);
        Role role = this.convertToDto(re);

        return role;
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> reList = (List) this.roleRepository.findAll();

        return reList.stream().map(roleEntity -> this.convertToDto(roleEntity)).collect(Collectors.toList());
    }

    @Override
    public Role save(Role role) throws RoleExcepction {
        RoleEntity roleEntity = this.convertToEntity(role);

        try {
            this.roleRepository.save(roleEntity);
        }
        catch (Exception e){
            throw new RoleExcepction("Error saving role", new RoleException());
        }

        return this.convertToDto(roleEntity);
    }

    @Override
    public int deleteByName(String name) {
        return this.roleRepository.deleteByName(name);
    }

    private Role convertToDto(RoleEntity roleEntity) {
        Role role = modelMapper.map(roleEntity, Role.class);
        return role;
    }

    private RoleEntity convertToEntity(Role role) {
        RoleEntity roleEntity = modelMapper.map(role, RoleEntity.class);
        return roleEntity;
    }

    @Override
    public Role update(Role role) throws RoleExcepction {
        RoleEntity roleEntity = this.roleRepository.findByName(role.getName());

        if (role == null) {
            throw new RoleExcepction("Role name does not exists", new RoleException());
        }

        roleEntity.setDescription(role.getDescription());

        try {
            this.roleRepository.save(roleEntity);
        } catch (Exception e) {
            throw new RoleExcepction("Error updating role", new RoleException());
        }

        return role;
    }
}

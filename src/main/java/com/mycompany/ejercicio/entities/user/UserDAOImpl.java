package com.mycompany.ejercicio.entities.user;

import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.cxf.exception.UserException;
import com.mycompany.ejercicio.entities.QuerysConfiguration;
import com.mycompany.ejercicio.entities.role.RoleEntity;
import com.mycompany.ejercicio.entities.role.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    QuerysConfiguration querysConfiguration;

    @Override
    public User findByLogin(String login) throws UserExcepction {
        UserEntity userEntity = this.userRepository.findByLogin(login);
        if(userEntity == null){
           throw new UserExcepction("User with login " + login + " not found!", new UserException());
        }

        User user = this.convertToDto(userEntity);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> reList = (List) this.userRepository.findAll();

        return reList.stream().map(userEntity -> this.convertToDto(userEntity)).collect(Collectors.toList());
    }

    @Override
    public User save(User user) throws UserExcepction {
        UserEntity userEntity = this.convertToEntity(user);

        try {
            this.userRepository.save(userEntity);
            List<RoleEntity> roles = this.roleRepository.findAllByNameIn(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
            roles.forEach(roleEntity -> userEntity.addRole(roleEntity));
            this.userRepository.save(userEntity);
        }
        catch (DataIntegrityViolationException dive){
            throw new UserExcepction("A user with that login already exists.", new UserException());
        }
        catch (Exception e) {
            throw new UserExcepction("Error saving user", new UserException());
        }

        return this.convertToDto(userEntity);
    }

    @Override
    public int deleteByLogin(String login) {
        return this.userRepository.deleteByLogin(login);
    }

    @Override
    public User update(User user) throws UserExcepction {
        UserEntity userEntity = this.userRepository.findByLogin(user.getLogin());

        if (user == null) {
            throw new UserExcepction("User login does not exists", new UserException());
        }

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        List<RoleEntity> roles = this.roleRepository.findAllByNameIn(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
        userEntity.setRoles(new HashSet<>());
        roles.forEach(roleEntity -> userEntity.addRole(roleEntity));

        try {
            this.userRepository.save(userEntity);
        } catch (Exception e) {
            throw new UserExcepction("Error updating user", new UserException());
        }

        return user;
    }

    private User convertToDto(UserEntity userEntity) {
        User user = modelMapper.map(userEntity, User.class);

        user.getRoles().addAll(userEntity.getRoles().stream().map(roleEntity -> {
            Role role = modelMapper.map(roleEntity, Role.class);
            return role;
        }).collect(Collectors.toList()));

        return user;
    }

    private UserEntity convertToEntity(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setRoles(new HashSet<>());
        return userEntity;
    }
}

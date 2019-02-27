package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.UserException;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.datatypes.User;

import java.util.List;

public interface UserDAO {

    User findByLogin(String login) throws UserException, DataAccessException;

    List<User> findAll() throws DataAccessException;

    User save(User ce) throws UserException, DataAccessException;

    int deleteByLogin(String login) throws DataAccessException;

    User update(User user) throws UserException, DataAccessException;

    int clearAllRolesToUserByLogin(String login) throws DataAccessException;

    int addRoleToUserByLogin(String login, String roleName) throws DataAccessException;
}

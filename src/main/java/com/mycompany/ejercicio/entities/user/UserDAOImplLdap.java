package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.UserException;
import com.mycompany.ejercicio.cxf.datatypes.User;

import java.util.List;

public class UserDAOImplLdap implements UserDAO{
    @Override
    public User findByLogin(String login) throws UserException, DataAccessException {
        return null;
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        return null;
    }

    @Override
    public User save(User ce) throws UserException, DataAccessException {
        return null;
    }

    @Override
    public int deleteByLogin(String login) throws DataAccessException {
        return 0;
    }

    @Override
    public User update(User user) throws UserException, DataAccessException {
        return null;
    }

    @Override
    public int clearAllRolesToUserByLogin(String login) throws DataAccessException {
        return 0;
    }

    @Override
    public int addRoleToUserByLogin(String login, String roleName) throws DataAccessException {
        return 0;
    }
}

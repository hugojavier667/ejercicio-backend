package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.datatypes.User;

import java.util.List;

public interface UserDAO {

    User findByLogin(String login);

    List<User> findAll();

    User save(User ce) throws UserExcepction;

    int deleteByLogin(String login);

    User update(User user) throws UserExcepction;
}

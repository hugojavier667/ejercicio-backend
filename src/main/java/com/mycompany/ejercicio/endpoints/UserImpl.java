package com.mycompany.ejercicio.endpoints;

import com.mycompany.ejercicio.cxf.User;
import com.mycompany.ejercicio.cxf.UserPortType;

public class UserImpl implements UserPortType {

    @Override
    public User userDetails(int id) {
        User user = new User();
        user.setId(id);
        user.setEmail("hugo@tm.cupet.cu");
        user.setLogin("hugo");

        return user;
    }

    @Override
    public User listAllUsers() {
        User user = new User();
        user.setId(0);
        user.setEmail("list@tm.cupet.cu");
        user.setLogin("list");

        return user;
    }
}

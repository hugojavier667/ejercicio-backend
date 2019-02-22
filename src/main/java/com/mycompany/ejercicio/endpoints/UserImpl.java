package com.mycompany.ejercicio.endpoints;

import com.mycompany.ejercicio.cxf.User;
import com.mycompany.ejercicio.cxf.UserServiceSoapPortType;
import com.mycompany.ejercicio.entities.IUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Holder;

public class UserImpl implements UserServiceSoapPortType {

    @Autowired
    IUser userRepository;

    @Override
    public void createUser(Holder<User> user) {
        userRepository.save(new com.mycompany.ejercicio.entities.User(user.value));
    }

    @Override
    public User userDetails(int id) {
        User user = new User();
        com.mycompany.ejercicio.entities.User user1 = userRepository.findById(id).get();

        user.setId(user1.getId());
        user.setEmail(user1.getEmail());
        user.setLogin(user1.getLogin());
        user.setName(user1.getName());

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

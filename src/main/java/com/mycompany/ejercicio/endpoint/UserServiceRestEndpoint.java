package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.entities.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserServiceRestEndpoint {

    @Autowired
    UserDAO userDAO;

    @GetMapping
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) throws UserExcepction {
        return userDAO.save(user);
    }

    @GetMapping(value = "/{login}")
    public User getUserByLogin(@PathVariable String login){
        return userDAO.findByLogin(login);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user) throws UserExcepction {
        return userDAO.update(user);
    }

    @DeleteMapping(value = "/{login}")
    public int deleteUserByLogin(@PathVariable String login){
        return userDAO.deleteByLogin(login);
    }
}

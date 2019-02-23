package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.UserServiceSoapPortType;
import com.mycompany.ejercicio.cxf.user.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceEndpoint implements UserServiceSoapPortType {

    @Autowired
    UserServiceController userServiceController;

    @Override
    public AddUserResponse createUser(AddUser parameters) throws UserExcepction {
        return this.userServiceController.addUser(parameters.getAddUserRequest());
    }

    @Override
    public GetUserByLoginResponse userDetails(GetUserByLogin parameters) throws UserExcepction {
        return this.userServiceController.getUserByLogin(parameters.getUserRequest());
    }

    @Override
    public DeleteUserReturn deleteUser(DeleteUserRequest deleteUserRequest) throws UserExcepction {
        return this.userServiceController.deleteUserByLogin(deleteUserRequest);
    }

    @Override
    public UsersReturn getUsers() {
        return this.userServiceController.getUsers();
    }

    @Override
    public AddUserReturn updateUser(UpdateUserRequest updateUserRequest) throws UserExcepction {
        return this.userServiceController.updateUser(updateUserRequest);
    }
}

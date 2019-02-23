package com.mycompany.ejercicio.endpoint;

import com.mycompany.ejercicio.cxf.datatypes.ArrayOfUser;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.cxf.user.*;
import com.mycompany.ejercicio.entities.user.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    public GetUserByLoginResponse getUserByLogin(UserRequest userRequest) {

        UserReturn rr = new UserReturn();
        String login = userRequest.getLogin();

        User user = userDAO.findByLogin(login);

        if (user != null) {
            rr.setSuccess(true);
            rr.setUserResult(user);
        } else {
            rr.setSuccess(false);
            rr.setErrorCode(101);//user not found
        }

        GetUserByLoginResponse getUserByIdResponse = new GetUserByLoginResponse();
        getUserByIdResponse.setGetUserByLoginResult(rr);

        return getUserByIdResponse;
    }

    public UsersReturn getUsers() {

        List<User> userList = userDAO.findAll();
        UsersReturn rr = new UsersReturn();
        ArrayOfUser ar = new ArrayOfUser();
        ar.getUser().addAll(userList);

        rr.setSuccess(true);
        rr.setUsersResult(ar);

        return rr;
    }

    AddUserResponse addUser(AddUserRequest addUserRequest) {

        User user = addUserRequest.getUser();
        AddUserReturn addUserReturn = new AddUserReturn();
        try {
            addUserReturn.setSuccess(true);
            this.userDAO.save(user);
        } catch (Exception e) {
            //save was not possible due to validations (unique login constraint, for example)
            addUserReturn.setSuccess(false);
            addUserReturn.setErrorCode(102);
            addUserReturn.setErrorMessage(e.getMessage());//this error message must be adapted to a friendly message
        }

        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setAddUserResult(addUserReturn);
        return addUserResponse;
    }

    DeleteUserReturn deleteUserByLogin(DeleteUserRequest deleteUserRequest) {

        String login = deleteUserRequest.getLogin();
        int affectedRows = this.userDAO.deleteByLogin(login);
        DeleteUserReturn dcr = new DeleteUserReturn();

        if (affectedRows > 0) {
            dcr.setSuccess(true);
        } else {
            dcr.setSuccess(false);
            dcr.setErrorCode(103);
        }

        return dcr;
    }

    public AddUserReturn updateUser(UpdateUserRequest updateUserRequest) {
        User user = updateUserRequest.getUser();
        AddUserReturn addUserReturn = new AddUserReturn();
        try {
            addUserReturn.setSuccess(true);
            this.userDAO.update(user);
        } catch (Exception e) {
            //save was not possible due to validations (unique login constraint, for example)
            addUserReturn.setSuccess(false);
            addUserReturn.setErrorCode(102);
            addUserReturn.setErrorMessage(e.getMessage());//this error message must be adapted to a friendly message
        }

        return addUserReturn;
    }
}

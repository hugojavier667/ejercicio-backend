package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.cxf.exception.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findByLogin(String name) {
        UserEntity userEntity = this.userRepository.findByLogin(name);
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
        } catch (Exception e) {
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

        try {
            this.userRepository.save(userEntity);
        } catch (Exception e) {
            throw new UserExcepction("Error updating user", new UserException());
        }

        return user;
    }

    private User convertToDto(UserEntity userEntity) {
        User user = modelMapper.map(userEntity, User.class);
        return user;
    }

    private UserEntity convertToEntity(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        return userEntity;
    }
}

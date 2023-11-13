package com.example.myshoppinglistapi.services;

import com.example.myshoppinglistapi.entities.User;
import com.example.myshoppinglistapi.exceptions.UserExistException;
import com.example.myshoppinglistapi.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAllUser{
        return userRepository.findAll();
    }

    public User findById(String email, String password){
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return (user.isPresent()) ? user.get() : throw new ObjectNotFoundException("Usuário não encontrado! email: "+email);
    }

    public User saveUser(User user){
        try{
            findById(user.getEmail(), user.getPassword());
        }catch (UserExistException e) {
            throw new RuntimeException(e);
        }catch (ObjectNotFoundException objectNotFoundException){
            return userRepository.save(user);
        }
    }

    public User updateUser(User user){
        try{
            User userOld = findById(user.getEmail(), user.getPassword());

            userOld.setName(user.getName());
            userOld.setNickName(user.getNickName());
            userOld.setIdAvatar(user.getIdAvatar());

            return userRepository.save(userOld);
        }catch (ObjectNotFoundException objectNotFoundException){
            throw objectNotFoundException;
        }
    }
}

package com.example.myshoppinglistapi.services;

import com.example.myshoppinglistapi.entities.User;
import com.example.myshoppinglistapi.exceptions.ObjectNotFoundException;
import com.example.myshoppinglistapi.exceptions.UserExistException;
import com.example.myshoppinglistapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findById(String email, String password) throws ObjectNotFoundException {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado! email: " + email);
        }
    }

    public User saveUser(User user) throws UserExistException {
        try{
            findById(user.getEmail(), user.getPassword());
            throw new UserExistException("Usuário já existe!");
        }catch (ObjectNotFoundException objectNotFoundException){
            return userRepository.save(user);
        }
    }

    public User updateUser(User user) throws ObjectNotFoundException {
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

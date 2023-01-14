package com.keMoleseng.bookapiv2.service;

import com.keMoleseng.bookapiv2.exceptions.UsernameAlreadyExistsException;
import com.keMoleseng.bookapiv2.model.User;
import com.keMoleseng.bookapiv2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User newUser) {

        try{
            newUser.setPassword(newUser.getPassword());

            //Username must be unique
            newUser.setUsername(newUser.getUsername());
            //mke sure password and confirm password match
            //no persist confirm password
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username : "+newUser.getUsername()+" already exists.");
        }
    }
}

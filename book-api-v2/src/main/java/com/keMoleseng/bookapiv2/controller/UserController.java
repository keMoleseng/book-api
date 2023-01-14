package com.keMoleseng.bookapiv2.controller;

import com.keMoleseng.bookapiv2.Validator.UserValidator;
import com.keMoleseng.bookapiv2.model.Book;
import com.keMoleseng.bookapiv2.model.User;
import com.keMoleseng.bookapiv2.repository.UserRepository;
import com.keMoleseng.bookapiv2.service.MapValidationService;
import com.keMoleseng.bookapiv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    @Autowired
    private MapValidationService mapValidationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    //register user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user, BindingResult result) {
        //Validate that passwords match

        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);

        if(errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}

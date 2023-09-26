package com.payments.payments.application.controllers;

import com.payments.payments.domain.user.dtos.CreateUserDTO;
import com.payments.payments.domain.user.entities.User;
import com.payments.payments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(service.createUser(createUserDTO));
    }
}

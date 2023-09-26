package com.payments.payments.services;

import com.payments.payments.domain.user.dtos.CreateUserDTO;
import com.payments.payments.domain.user.entities.User;
import com.payments.payments.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(CreateUserDTO userDTO) {
        return userRepository.save(convertUserDTOtoEntity(userDTO));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void increaseUserBalance(User user, BigDecimal value) {
        BigDecimal newBalance = user.getBalance().add(value);
        user.setBalance(newBalance);
    }

    public void decreaseUserBalance(User user, BigDecimal value) {
        BigDecimal newBalance = user.getBalance().subtract(value);
        user.setBalance(newBalance);
    }

    private User convertUserDTOtoEntity(CreateUserDTO createUserDTO) {
        return User.builder()
                .userType(createUserDTO.userType())
                .balance(createUserDTO.balance())
                .email(createUserDTO.email())
                .document(createUserDTO.document())
                .firstName(createUserDTO.firstName())
                .lastName(createUserDTO.lastName())
                .password(createUserDTO.password())
                .build();
    }

}
